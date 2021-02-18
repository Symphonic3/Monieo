package zoo;

import java.util.List;

public class zoo {

	public List<monkey> monkeys;
	
	public zoo() {
		
		for(int i = 0; i < 4; i++) {
			monkeys.add(new monkey());
		}
	}
}