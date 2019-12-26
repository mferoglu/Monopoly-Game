import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class GetCellFactory {


	public Cell getCell(String cellType,int location, Player owner, String color, int priceOfTheCell, int rent, String name,CommunityCard[] communityCards,ChanceCard[] chanceCards ){
		if (cellType == null){
			return null;
		}
		if (cellType.equalsIgnoreCase("StartingCell")){
			return new StartingCell();
		}
		else if (cellType.equalsIgnoreCase("PropertyCell")){
			return new PropertyCell(location, owner, color,priceOfTheCell, rent, name);
		}
		else if (cellType.equalsIgnoreCase("StationCell")){
			return new StationCell(location, owner,priceOfTheCell,name,rent);
		}
		else if(cellType.equalsIgnoreCase("JailCell")){
			return new JailCell(location);
		}
		else if(cellType.equalsIgnoreCase("CommunityCardCell")){
			return new CommunityCardCell(location, communityCards);
		}
		else if(cellType.equalsIgnoreCase("ChanceCardCell")){
			return new CommunityCardCell(location, communityCards);
		}
		else if(cellType.equalsIgnoreCase("RegularCell")){
			return new RegularCell(location, owner);
		}
		else if(cellType.equalsIgnoreCase("ElectricCell")){
			return new ElectricCell(location, priceOfTheCell);
		}
		else if(cellType.equalsIgnoreCase("WaterCell")){
			return new WaterCell(location, priceOfTheCell);
		}
		else{
			return new RegularCell(location,owner);
		}

	}

}
