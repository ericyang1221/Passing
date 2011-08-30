package ascript.common.util
{
	import mx.collections.ArrayCollection;
	import mx.containers.Panel;
	import mx.controls.Alert;
	
	public class Stack
	{
		var stack:ArrayCollection;
		
		public function Stack()
		{
			stack = new ArrayCollection();
		}
		
		public function size():int{
			return stack.length;
		}
		
		public function push(obj:Object):void{
			stack.addItem(obj);
		}
		
		public function pop():Object{
			var returnObj:Object = null;
			if(stack.length > 0){
				returnObj = stack.getItemAt(stack.length - 1);
				stack.removeItemAt(stack.length - 1);
			}else{
				throw new Error("Stack null point exception.");
			}
			return returnObj;
		}

	}
}