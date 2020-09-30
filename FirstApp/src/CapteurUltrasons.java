public class CapteurUltrasons {
	private long distance;
	
	public CapteurUltrasons(Perception perception) {
		this.distance = perception.getDistanceCapteurUltrasons();
	}
	
	public boolean VerifSiObjetDetecteEstUnPalet() {
		boolean palet = true;
		//avancer
		/*if (distance < valeurOuOnVoitPlusLePalet) {
			palet = false;
		}*/
		return palet;
	}
	
	public long getDistance() {
		return this.distance;
	}
	
	public void setDistance(long distance) {
		this.distance = distance;
	}
	
	public boolean murOuRobotDetecte() {
		boolean murOuRobot = false;
		//avancer
		/*if (distance < valeurOuOnVoitPlusLePalet) {
			murOuRobot = true;
		}*/
		return murOuRobot;
	}
}
