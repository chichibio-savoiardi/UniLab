package a3b.climate.utils.result;

public class Panic extends Error {
	public Panic(String msg) {
		super(msg);
	}

	public Panic(Throwable t) {super(t);}
}
