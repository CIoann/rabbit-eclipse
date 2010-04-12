/*
 * Copyright 2010 The Rabbit Eclipse Plug-in Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package rabbit.ui.internal.pages;

import static rabbit.ui.MillisConverter.toDefaultString;

import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

// TODO test
public class ResourcePageTableLabelProvider extends DecoratingLabelProvider
    implements ITableLabelProvider, IColorProvider {

  private final ResourcePageContentProvider contents;

  public ResourcePageTableLabelProvider(
      ResourcePageContentProvider contentProvider) {
    super(new ResourcePageLabelProvider(), null);

    contents = contentProvider;
  }

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    if (columnIndex == 0)
      return super.getImage(element);
    else
      return null;
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    switch (columnIndex) {
    case 0:
      String text = super.getText(element);
      return text;

    case 1:
      if (contents.shouldPaint(element))
        return toDefaultString(contents.getValue(element));

    default:
      return null;
    }
  }
}