package resourse;

import java.awt.Dimension;

public class SetSizeByPercent {
	private Dimension dimension;

	public SetSizeByPercent(Dimension dimension) {
		super();
		this.dimension = dimension;
	}
	public int getWidthByPercent(double percent) {
		int size;
		size =(int) (percent*dimension.width)/100;
		return size;
	}
	public int getHeightByPercent(double percent) {
		int size;
		size =(int) (percent*dimension.height)/100;
		return size;
	}
}
