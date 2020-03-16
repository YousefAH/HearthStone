package model.cards;


abstract public class Card
{
	private String name;
	private int manaCost;
	private Rarity rarity;
	
	public Card() {}
	
	public Card(String name,int manaCost,Rarity rarity)
	{
		this.name = name;
		if(manaCost>10)
		this.manaCost = 10;
		else if(manaCost <0)
			this.manaCost = 0;
		else
			this.manaCost = manaCost;
		this.rarity = rarity;
	}
	
	
	public boolean equals(Object o) 
	{
		return ((Card)o).name.equals(this.name) && ((Card)o).manaCost == this.manaCost && this.rarity== ((Card)o).rarity;
	}
	
	public String toString() 
	{
		return name+" " + manaCost+" " +" " + rarity;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = (manaCost > 0)? ((manaCost<=10)? manaCost:10):0; 
		
	}

	public Rarity getRarity() {
		return rarity;
	}

	public static void main(String[] args) {
		System.out.println();
	}
}
