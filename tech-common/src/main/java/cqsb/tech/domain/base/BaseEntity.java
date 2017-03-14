package cqsb.tech.domain.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

	@MappedSuperclass
	public class BaseEntity implements Serializable {

		private static final long serialVersionUID = -436407378052568596L;
		
		@Id
	    @Column(length = 32, nullable = true)
	    @GeneratedValue(generator = "uuid")
	    @GenericGenerator(name = "uuid", strategy = "uuid")
		private String id;// ID
		
	    private Date createdate;// 创建日期
	    
	    private Date updatedate;// 修改日期
	    
	    private int isdel;//删除标志
	    
	    public static final int DELETED = 1;
	    
	    public static final int NOT_DELETED = 0;

		public int getIsdel() {
			return isdel;
		}

		public void setIsdel(int isdel) {
			this.isdel = isdel;
		}

		public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    @Column(updatable = false)
	    public Date getCreatedate() {
			return createdate;
		}

		public void setCreatedate(Date createdate) {
			this.createdate = createdate;
		}

		public Date getUpdatedate() {
			return updatedate;
		}

		public void setUpdatedate(Date updatedate) {
			this.updatedate = updatedate;
		}

		@Override
	    public int hashCode() {
	        return id == null ? System.identityHashCode(this) : id.hashCode();
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null) {
	            return false;
	        }
	        if (getClass().getPackage() != obj.getClass().getPackage()) {
	            return false;
	        }
	        final BaseEntity other = (BaseEntity) obj;
	        if (id == null) {
	            if (other.getId() != null) {
	                return false;
	            }
	        } else if (!id.equals(other.getId())) {
	            return false;
	        }
	        return true;
	    }
		
		
	}
	


	    
