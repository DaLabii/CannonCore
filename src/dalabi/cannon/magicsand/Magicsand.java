package dalabi.cannon.magicsand;

import java.util.UUID;

import org.bukkit.block.Block;

public class Magicsand {

	Block block;
	int material;
	byte data;
	int counter;
	UUID owner;

	public Magicsand(Block block, int material, byte data, int counter, UUID owner) {
		this.block = block;
		this.material = material;
		this.data = data;
		this.counter = counter;
		this.owner = owner;
	}

	public Block getBlock() {
		return this.block;
	}

	public int getMaterial() {
		return this.material;
	}

	public byte getData() {
		return this.data;
	}

	public int getCounter() {
		return this.counter;
	}

	public UUID getOwner() {
		return this.owner;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}
