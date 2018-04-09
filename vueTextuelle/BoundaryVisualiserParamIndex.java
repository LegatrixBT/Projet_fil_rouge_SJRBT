package vueTextuelle;

import control.ControlVisualiserParamIndex;

public class BoundaryVisualiserParamIndex {
	
	private ControlVisualiserParamIndex controlVisualiserParamIndex;
	
	public BoundaryVisualiserParamIndex(ControlVisualiserParamIndex controlVisualiserParamIndex) {
		this.controlVisualiserParamIndex = controlVisualiserParamIndex;
	}
	
	public void visualiserParamIndex() {
		System.out.println(controlVisualiserParamIndex.visualiserParamIndex());
	}
	
}
