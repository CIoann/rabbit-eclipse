package rabbit.ui.internal.pages;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import rabbit.ui.ColumnComparator;
import rabbit.ui.IPage;
import rabbit.ui.internal.RabbitUI;

/**
 * A page that contains a tree (can be used as a table) that is capable of
 * painting graphics.
 * <p>
 * The tree has a column containing numerical values, and a column used for
 * painting color bars. The length of the bar is determined by
 * {@link #getMaxValue()}, when the table viewer's data is changed, call
 * {@link #setMaxValue(double)} to indicated the new maximum value.
 * </p>
 * <p>
 * By default, the value column and the graph column is constructed after all
 * other columns, that means the index of the graph column is the last index,
 * and the index of the value column is last index - 1.
 * </p>
 */
public abstract class AbstractGraphTreePage implements IPage {

	private long maxValue;

	private TreeViewer viewer;
	private TreeColumn valueColumn;
	private TreeColumn graphColumn;
	private TreeColumn[] otherColumns;

	private Listener graphPainter = new Listener() {

		private boolean isLinux = System.getProperty("os.name").toLowerCase().contains("linux");

		@Override
		public void handleEvent(Event e) {

			TreeItem item = (TreeItem) e.item;
			Tree tree = item.getParent();

			int width = getWidth(tree, item);
			if (width == 0) {
				return;
			}
			int x = getX(tree);
			int y = e.y + 1;
			int height = e.height - 2;

			GC gc = e.gc;
			Color oldBackground = gc.getBackground();
			Color oldForeground = gc.getForeground();
			int oldAnti = gc.getAntialias();

			gc.setBackground(e.display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND));
			if (isLinux) {
				gc.fillRectangle(x, y, width, height);
			} else {
				gc.setAntialias(SWT.ON);
				gc.fillRectangle(x, y, 2, height);
				gc.fillRoundRectangle(x, y, width, height, 4, 4);
			}
			gc.setForeground(e.display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));
			gc.drawLine(x - 1, y - 1, x - 1, y + height);
			gc.drawLine(x + width, y - 1, x + width, y + height);

			gc.setBackground(oldBackground);
			gc.setForeground(oldForeground);
			gc.setAntialias(oldAnti);
		}

		/**
		 * Gets the x position for painting.
		 * 
		 * @param tree
		 *            The tree to be painted.
		 */
		private int getX(Tree tree) {
			int x = 0;
			for (int i : tree.getColumnOrder()) {

				TreeColumn column = tree.getColumn(i);
				if (column != getGraphColumn()) {
					x += column.getWidth();
				} else {
					break;
				}
			}
			return x;
		}

		/**
		 * Gets the width in pixels for the paint.
		 * 
		 * @param tree
		 *            The tree to be painted.
		 * @param item
		 *            The tree item to be painted.
		 * @return The width in pixels.
		 */
		private int getWidth(Tree tree, TreeItem item) {
			int width = 0;
			try {
				double value = getValue(item.getData());// Double.parseDouble(item.getText(tree.indexOf(getValueColumn())));
				width = (int) (value * getGraphColumn().getWidth() / getMaxValue());
				width = ((value != 0) && (width == 0)) ? 2 : width;

				if (value != 0 && width < 2) {
					width = 2;
				} else if (width == getGraphColumn().getWidth()) {
					width -= 1;
				}
			} catch (NumberFormatException ex) {
			}
			return width;
		}
	};

	/**
	 * Constructor.
	 */
	public AbstractGraphTreePage() {
		setMaxValue(0);
	}

	@Override
	public void createContents(Composite parent) {
		viewer = new TreeViewer(parent, SWT.NONE);
		viewer.setContentProvider(createContentProvider());
		viewer.setLabelProvider(createLabelProvider());
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent e) {
				IStructuredSelection select = (IStructuredSelection) e.getSelection();
				Object o = select.getFirstElement();
				if (((ITreeContentProvider) viewer.getContentProvider()).hasChildren(o)) {
					viewer.setExpandedState(o, !viewer.getExpandedState(o));
				}
			}
		});

		final Tree tree = viewer.getTree();
		tree.setHeaderVisible(true);
		tree.addListener(SWT.PaintItem, graphPainter);
		tree.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				saveState();
			}
		});

		ColumnComparator sorter = createComparator(viewer);
		otherColumns = createColumns(tree);
		for (TreeColumn column : otherColumns) {
			column.addSelectionListener(sorter);
		}

		valueColumn = new TreeColumn(tree, SWT.RIGHT);
		valueColumn.setText(getValueColumnText());
		valueColumn.setWidth(100);
		valueColumn.setMoveable(true);
		valueColumn.addSelectionListener(sorter);

		graphColumn = new TreeColumn(tree, SWT.LEFT);
		graphColumn.setWidth(100);
		graphColumn.setMoveable(true);
		graphColumn.addSelectionListener(sorter);

		restoreState();
	}

	protected ColumnComparator createComparator(TreeViewer viewer) {
		return new ColumnComparator(viewer) {
			@Override
			protected int doCompare(Viewer v, Object e1, Object e2) {
				if (getSelectedColumn() == getValueColumn() || getSelectedColumn() == getGraphColumn()) {
					return (getValue(e1) > getValue(e2)) ? 1 : -1;
				}
				return super.doCompare(v, e1, e2);
			}
		};
	}

	/** Saves the state of the page. */
	protected void saveState() {
		IPreferenceStore store = RabbitUI.getDefault().getPreferenceStore();
		store.setValue(getClass().getSimpleName() + ".Graph", graphColumn.getWidth());
		store.setValue(getClass().getSimpleName() + ".Value", valueColumn.getWidth());
		for (TreeColumn column : otherColumns) {
			store.setValue(getClass().getSimpleName() + '.' + column.getText() + "Width", column.getWidth());
		}
	}

	/** Restores the state of the page. */
	protected void restoreState() {
		IPreferenceStore store = RabbitUI.getDefault().getPreferenceStore();
		int width = store.getInt(getClass().getSimpleName() + ".Graph");
		if (width > 0) {
			graphColumn.setWidth(width);
		}
		width = store.getInt(getClass().getSimpleName() + ".Value");
		if (width > 0) {
			valueColumn.setWidth(width);
		}
		for (TreeColumn column : otherColumns) {
			width = store.getInt(getClass().getSimpleName() + '.' + column.getText() + "Width");
			if (width > 0) {
				column.setWidth(width);
			}
		}
	}

	/**
	 * Gets the current maximum value used for painting.
	 * <p>
	 * Note that the value return is for painting only, therefore the value
	 * returned may or may not be the maximum of all {@link #getValue(Object)}
	 * returned.
	 * </p>
	 * 
	 * @return The maximum value in milliseconds.
	 */
	public long getMaxValue() {
		return maxValue;
	}

	/**
	 * Sets the maximum value for painting.
	 * 
	 * @param max
	 *            The value in milliseconds.
	 * @see #getGraphColumn()
	 */
	public void setMaxValue(long max) {
		maxValue = max;
	}

	/**
	 * Gets the column that is used for painting.
	 * 
	 * @return The column that is used for painting.
	 */
	public TreeColumn getGraphColumn() {
		return graphColumn;
	}

	/**
	 * Gets the column for displaying usage information.
	 * 
	 * @return The column for displaying the usage information.
	 */
	public TreeColumn getValueColumn() {
		return valueColumn;
	}

	/**
	 * Gets the viewer of this page.
	 * 
	 * @return The viewer.
	 */
	public TreeViewer getViewer() {
		return viewer;
	}

	/**
	 * Gets the usage value of the given object.
	 * 
	 * @param o
	 *            The object to get value for.
	 * @return The value in milliseconds, or 0 if this object has no usage
	 *         value.
	 */
	abstract long getValue(Object o);

	/**
	 * Creates an content provider for the viewer.
	 * 
	 * @return An content provider.
	 * @see #getViewer()
	 */
	protected abstract ITreeContentProvider createContentProvider();

	/**
	 * Creates an label provider for the viewer.
	 * 
	 * @return An label provider.
	 * @see #getViewer()
	 */
	protected abstract ITableLabelProvider createLabelProvider();

	/**
	 * Creates the additional columns for the tree.
	 * 
	 * @param tree
	 *            The tree to create columns for.
	 * @return The created columns.
	 */
	protected abstract TreeColumn[] createColumns(Tree tree);

	/**
	 * Gets the title text for the Tree column displaying the usage.
	 * 
	 * @return The title text.
	 */
	protected abstract String getValueColumnText();
}