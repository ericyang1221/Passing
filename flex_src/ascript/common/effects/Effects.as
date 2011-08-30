package ascript.common.effects
{
	import mx.effects.Blur;
	import mx.effects.Fade;
	import mx.effects.Zoom;
	import mx.events.EffectEvent;
	
	import flash.events.MouseEvent;
	import flash.geom.Rectangle;
	
	public class Effects
	{
		private static var fadeObj:Fade = new Fade();
		private static var blurObj:Blur = new Blur();
		private static var zoomObj:Zoom = new Zoom();
		
		public function Effects()
		{
		}
		
		private static function getFadeObj():Fade{
			fadeObj.alphaFrom = 0;
			fadeObj.alphaTo = 1;
			fadeObj.duration = 200;
			return fadeObj;
		}
		
		public static function doFadeIn(obj:Array):void{
			getFadeObj().play(obj,false);
		}
		
		private static function getBlurObj():Blur{
			blurObj.blurXFrom = 0;
			blurObj.blurXTo = 5;
			blurObj.blurYFrom = 0;
			blurObj.blurYTo = 5;
			return blurObj;
		}
		
		public static function doBlurIn(obj:Array):void{
			getBlurObj().play(obj,false);
		}
		
		public static function doBlurOut(obj:Array):void{
			getBlurObj().play(obj,true);
		}

		public static function doZoomIn(obj:Object):void{
			zoomObj.zoomHeightFrom = 0;
			zoomObj.zoomWidthFrom = 0;
			zoomObj.zoomHeightTo = 1;
			zoomObj.zoomWidthTo = 1;
			zoomObj.duration = 100;
			zoomObj.play([obj],false);
		}
		
		public static function doZoomOut(obj:Object):void{
			zoomObj.zoomHeightFrom = 1;
			zoomObj.zoomWidthFrom = 1;
			zoomObj.zoomHeightTo = 0;
			zoomObj.zoomWidthTo = 0;
			zoomObj.duration = 100;
			zoomObj.addEventListener(EffectEvent.EFFECT_END,function tmprm():void{
				if(obj.parent){
					obj.parent.parent.removeChild(obj.parent);
					zoomObj.removeEventListener(EffectEvent.EFFECT_END,tmprm,false);
				}
			},false);
			zoomObj.play([obj],false);
		}
		
		public static function doDrag(control:Object,controled:Object):void{
			var systemManager:Object = controled.parent.parent;
			control.addEventListener(MouseEvent.MOUSE_DOWN,function():void{onMouseDown(controled);});
			systemManager.addEventListener(MouseEvent.MOUSE_UP,function():void{onMouseUp(controled);});
		}
		
		private static function onMouseDown(obj:Object):void{
			toTopFloor(obj);
			var browser:Object = obj.parent;
			obj.startDrag(false,new Rectangle(0,0,browser.width-obj.width,browser.height-obj.height));
		}
		
		private static function onMouseUp(obj:Object):void{
			obj.stopDrag();
			obj.setStyle("left",obj.x);
			obj.setStyle("top",obj.y);
		}
		
		public static function toTopFloor(obj:Object):void{
			var browser:Object = obj.parent;
			var maxIndex:int = browser.numChildren-1;
		//----------将当前所点控件调到顶层（其他控件之上）-----------------------------
			if(browser.getChildIndex(obj) != maxIndex){
		//swapChildren（）:交换两个子项的index值
				browser.swapChildren(obj,browser.getChildAt(maxIndex));
			}
		}

	}
}