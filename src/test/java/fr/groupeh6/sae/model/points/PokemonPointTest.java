package fr.groupeh6.sae.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.BooleanColumn;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NotNormalizableException;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.columns.StringColumn;
import fr.groupeh6.sae.model.datas.pokemon.EnumType;
import fr.groupeh6.sae.model.datas.pokemon.PokemonPoint;

class PokemonPointTest {

	IPoint poke1 = new PokemonPoint("Swablu", 40, 5120, 255.0, 60, 600000, 45, 75, 50, EnumType.NORMAL,
			EnumType.FLYING, 1.2, false);
	IPoint poke2 = new PokemonPoint("Azelf", 125, 20480, 3.0, 70, 1250000, 75, 70, 115, EnumType.PSYCHIC, null, 0.3,
			true);
	IPoint poke3 = new PokemonPoint("Gallade", 165, 5120, 45.0, 95, 1250000, 68, 115, 110, EnumType.PSYCHIC,
			EnumType.FIGHTING, 52.0, false);
	IPoint pokeNull = new PokemonPoint();

	StringColumn name = new StringColumn("name");
	NumberColumn attack = new NumberColumn("attack");
	NumberColumn baseEggSteps = new NumberColumn("base_egg_steps");
	NumberColumn captureRate = new NumberColumn("capture_rate");
	NumberColumn defense = new NumberColumn("defense");
	NumberColumn expGrowth = new NumberColumn("experience_growth");
	NumberColumn hp = new NumberColumn("hp");
	NumberColumn spAttack = new NumberColumn("sp_attack");
	NumberColumn spDefense = new NumberColumn("sp_defense");
	EnumColumn<EnumType> type1 = new EnumColumn<EnumType>("type1", EnumType.class);
	EnumColumn<EnumType> type2 = new EnumColumn<EnumType>("type2", EnumType.class);
	NumberColumn speed = new NumberColumn("speed");
	BooleanColumn legendary = new BooleanColumn("is_legendary");

	
	@BeforeEach
	void setUp (){
		attack.update(30);
		attack.update(150);
		
		baseEggSteps.update(5120);
		baseEggSteps.update(30000);
		
		captureRate.update(3.0);
		captureRate.update(255);
		
		defense.update(35);
		defense.update(120);
		
		expGrowth.update(800000);
		expGrowth.update(1250000);
		
		hp.update(35);
		hp.update(100);
		
		spAttack.update(25);
		spAttack.update(100);
		
		spDefense.update(30);
		spDefense.update(140);
		
		speed.update(1.2);
		speed.update(150);
	}
	
	
	@Test
	void toStringTest() {
		assertEquals("Pokemon [name=Swablu, attack=40.0, eggSteps=5120.0, captureRate=255.0, defense=60.0, experience=600000.0, hp=45.0, spAttack=75.0, spDefense=50.0, type1=NORMAL, type2=FLYING, speed=1.2, legendary=false]",poke1.toString());
	}

	@Test
	void test_getValue() {
		assertEquals("Swablu", poke1.getValue(name));
		assertEquals(125.0, poke2.getValue(attack));
		assertEquals(5120.0, poke3.getValue(baseEggSteps));
		assertEquals(255.0, poke1.getValue(captureRate));
		assertEquals(70.0, poke2.getValue(defense));
		assertEquals(1250000.0, poke3.getValue(expGrowth));
		assertEquals(45.0, poke1.getValue(hp));
		assertEquals(70.0, poke2.getValue(spAttack));
		assertEquals(110.0, poke3.getValue(spDefense));
		assertEquals(EnumType.NORMAL, poke1.getValue(type1));
		assertEquals(null, poke2.getValue(type2));
		assertEquals(52.0, poke3.getValue(speed));
		assertEquals(false, poke1.getValue(legendary));
		AbstractColumn fake = new BooleanColumn("fake");
		assertEquals(null,poke1.getValue(fake));
	}

	@Test
	void getNormalizedValueTest() {
		assertThrows(NotNormalizableException.class, () -> poke1.getNormalizedValue(name));
		assertEquals(0.0833,poke1.getNormalizedValue(attack),0.0001);
		assertEquals(0.6173,poke2.getNormalizedValue(baseEggSteps),0.0001);
		assertEquals(0.1666,poke3.getNormalizedValue(captureRate),0.0001);
		assertEquals(0.2941,poke1.getNormalizedValue(defense),0.0001);
		assertEquals(1,poke2.getNormalizedValue(expGrowth),0.0001);
		assertEquals(0.5076,poke3.getNormalizedValue(hp),0.0001);
		assertEquals(0.6666,poke1.getNormalizedValue(spAttack),0.0001);
		assertEquals(0.7272,poke3.getNormalizedValue(spDefense),0.0001);
		assertEquals(0.8333,poke3.getNormalizedValue(type1),0.0001);
		assertEquals(0.0,poke2.getNormalizedValue(type2));
		assertEquals(0.3413,poke3.getNormalizedValue(speed),0.0001);
		
	}
	
	
	@Test
	void set_Value() {
		assertEquals("Swablu", poke1.getValue(name));
		poke1.setValue(name,"Joe");
		assertEquals("Joe",poke1.getValue(name));
		
		assertEquals(40.0,poke1.getValue(attack));
		poke1.setValue(attack,50.0);
		assertEquals(50.0,poke1.getValue(attack));
		
		assertEquals(5120.0,poke1.getValue(baseEggSteps));
		poke1.setValue(baseEggSteps, 6500.0);
		assertEquals(6500.0,poke1.getValue(baseEggSteps));
		
		assertEquals(255.0,poke1.getValue(captureRate));
		poke1.setValue(captureRate, 100.0);
		assertEquals(100.0,poke1.getValue(captureRate));
		
		assertEquals(60.0, poke1.getValue(defense));
		poke1.setValue(defense, 20.0);
		assertEquals(20.0, poke1.getValue(defense));
		
		assertEquals(600000.0,poke1.getValue(expGrowth));
		poke1.setValue(expGrowth, 350000.0);
		assertEquals(350000.0,poke1.getValue(expGrowth));
		
		assertEquals(45.0,poke1.getValue(hp));
		poke1.setValue(hp, 60.0);
		assertEquals(60.0,poke1.getValue(hp));
		
		assertEquals(75.0,poke1.getValue(spAttack));
		poke1.setValue(spAttack, 90.0);
		assertEquals(90.0,poke1.getValue(spAttack));
		
		assertEquals(50.0,poke1.getValue(spDefense));
		poke1.setValue(spDefense, 25.0);
		assertEquals(25.0,poke1.getValue(spDefense));
		
		assertEquals(EnumType.NORMAL,poke1.getValue(type1));
		poke1.setValue(type1, EnumType.DARK);
		assertEquals(EnumType.DARK,poke1.getValue(type1));
		
		assertEquals(EnumType.FLYING,poke1.getValue(type2));
		poke1.setValue(type2, EnumType.FAIRY);
		assertEquals(EnumType.FAIRY,poke1.getValue(type2));
		
		assertEquals(1.2,poke1.getValue(speed));
		poke1.setValue(speed, 10.0);
		assertEquals(10.0,poke1.getValue(speed));
		
		assertEquals(false,poke1.getValue(legendary));
		poke1.setValue(legendary, true);
		assertEquals(true,poke1.getValue(legendary));
	}
	

}
