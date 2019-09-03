package ClassLoaderDemo;

public class Sample {
	private Sample Instance=null;
	
	public void setSample(Sample in){
		this.setInstance((Sample)in);
	}

	public Sample getInstance() {
		return Instance;
	}

	public void setInstance(Sample instance) {
		Instance = instance;
	}

}
