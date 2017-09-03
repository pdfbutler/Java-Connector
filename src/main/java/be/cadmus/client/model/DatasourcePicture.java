package be.cadmus.client.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;

import be.cadmus.client.generic.Constants.DataSourceType;

public class DatasourcePicture extends AbstractDatasource {
	
	protected DatasourcePicture() {
		this.setType(DataSourceType.LIST);
	}

	private List<Picture> data = new ArrayList<Picture>();
	
	public List<Picture> getData() {
		return data;
	}
	
	public Picture addPicture() {
		Picture picture = new Picture();
		this.data.add(picture);
		
		return picture;
	}
	
	public class Picture {
		String base64;
		String name;
		String parentId;
		
		public String getBase64() {
			return base64;
		}
		public void setBase64(String base64) {
			this.base64 = base64;
		}
		public void setFile(File file) throws IOException {
			this.base64 = Base64Utils.encodeToString( FileUtils.readFileToByteArray(file) );
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
		
	}
}
