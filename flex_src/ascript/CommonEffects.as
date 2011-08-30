import mx.effects.Zoom;
import mx.events.EffectEvent;

private static var zoom:Zoom = new Zoom();

public static function doZoomIn(obj:Object):void{
	zoom.zoomHeightFrom = 0;
	zoom.zoomWidthFrom = 0;
	zoom.zoomHeightTo = 1;
	zoom.zoomWidthTo = 1;
	zoom.duration = 100;
	zoom.play([obj],false);
}

public static function doZoomOut(obj:Object):void{
	zoom.zoomHeightFrom = 1;
	zoom.zoomWidthFrom = 1;
	zoom.zoomHeightTo = 0;
	zoom.zoomWidthTo = 0;
	zoom.duration = 100;
	zoom.addEventListener(EffectEvent.EFFECT_END,function tmprm():void{
		if(obj.parent){
			obj.parent.parent.removeChild(obj.parent);
			zoom.removeEventListener(EffectEvent.EFFECT_END,tmprm,false);
		}
	},false);
	zoom.play([obj],false);
}