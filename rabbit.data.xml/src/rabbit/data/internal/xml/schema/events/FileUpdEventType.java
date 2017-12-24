package rabbit.data.internal.xml.schema.events;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fileEventType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fileEventType">
 *   &lt;complexContent>
 *     &lt;extension base="{}durationEventType">
 *       &lt;attribute name="filePath" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileUpdEventType")
public class FileUpdEventType
    extends DurationEventType
{

    @XmlAttribute(required = true)
    protected String id;
    @XmlAttribute(required = true)
    protected String sid;
    @XmlAttribute(required = true)
    protected String category;
    @XmlAttribute(required = true)
    protected String name;
    
    @XmlAttribute(required = true)
    protected String act;
    
    public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(required = true)
    protected String tsStart;
    @XmlAttribute(required = true)
    protected String tsEnd;

    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
     * Gets the value of the filePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilePath() {
        return id;
    }

    /**
     * Sets the value of the filePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilePath(String value) {
        this.id = value;
    }

	public String getTsStart() {
		return tsStart;
	}

	public void setTsStart(String tsStart) {
		this.tsStart = tsStart;
	}

	public String getTsEnd() {
		return tsEnd;
	}

	public void setTsEnd(String tsEnd) {
		this.tsEnd = tsEnd;
	}

}
