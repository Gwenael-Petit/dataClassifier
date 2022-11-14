package fr.groupeh6.sae.model.datas;

import fr.groupeh6.sae.model.Dataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

public enum StoredDatas implements IStoredDatas {
	
	IRIS() {
		@Override
		public Dataset dataset() {
			return new IrisDataset();
		}
		@Override
		public IPoint point() {
			return new IrisPoint();
		}
	};

}
