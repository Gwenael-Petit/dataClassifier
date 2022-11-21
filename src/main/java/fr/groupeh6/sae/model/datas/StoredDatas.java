package fr.groupeh6.sae.model.datas;

import fr.groupeh6.sae.model.Dataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;
import fr.groupeh6.sae.model.datas.pokemon.PokemonDataset;
import fr.groupeh6.sae.model.datas.pokemon.PokemonPoint;
import fr.groupeh6.sae.model.datas.titanic.TitanicDataset;
import fr.groupeh6.sae.model.datas.titanic.TitanicPoint;

public enum StoredDatas {
	
	IRIS() {
		@Override
		public Dataset dataset() {
			return new IrisDataset();
		}
	},
	TITANIC(){
		@Override
		public Dataset dataset() {
			return new TitanicDataset();
		}
	},
	POKEMON(){
		@Override
		public Dataset dataset() {
			return new PokemonDataset();
		}
	};
	
	public abstract Dataset dataset();

}
