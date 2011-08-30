package ascript.components
{
	import ascript.common.effects.Effects;
	import ascript.hibernate.beans.Jpword;
	import ascript.hibernate.beans.Jpwordexample;
	import ascript.hibernate.beans.Jpwordmeaning;
	
	import flash.events.MouseEvent;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.containers.Panel;
	import mx.containers.VBox;
	import mx.controls.Image;
	import mx.controls.Label;
	import mx.controls.LinkButton;
	import mx.controls.Spacer;
	import mx.controls.Text;

	public class SearchResultPanel extends Panel
	{
		/**
		 * _jpwordList contains a flag which marks the page to be normal(false) or no searched words(true).
		 * The other is the JpwordList.
		 * if no searched words,JpwordList saves the Associated jpwordlist.
		 * */
		private var _jpwordList:ArrayCollection;

		public function getJpwordList():ArrayCollection{
			return _jpwordList;
		}

		public function setJpwordList(jpwordList:ArrayCollection):void{
			_jpwordList = jpwordList;
		}

		public function SearchResultPanel()
		{
			super();
			_jpwordList = new ArrayCollection();
			_jpwordList.addItem(0);
			_jpwordList.addItem(new ArrayCollection());
		}
		
		public function createSearchResultDivByJpToCn(jpwordList:ArrayCollection):void{
			_jpwordList = new ArrayCollection();
			_jpwordList.addItem(0);
			_jpwordList.addItem(jpwordList);
			
			this.removeAllChildren();
			if(jpwordList.length>0){
				for(var i:int=0;i<jpwordList.length;i++){
					var jpword:Jpword = jpwordList.getItemAt(i) as Jpword;
					/*WordBox is the biggest container for a word.*/
					var wordBox:VBox = new VBox();
					wordBox.setStyle("fontSize","12");
					wordBox.setStyle("color","black");
					/*Word line includes word & ChineseOrJapaneseFlag & kana & addtonewwordImg*/
					var wordLine:HBox = new HBox();
					wordLine.setStyle("fontWeight","bold");
					wordLine.setStyle("fontSize","14");
					/*kana*/
					var kanaText:Text = new Text();
					kanaText.setStyle("color","red");
					kanaText.text = "【"+jpword.kana+"】";
					/*ChineseOrJapaneseFlag*/
					var flagLabel:Label = new Label();
					flagLabel.width = 14;
					flagLabel.opaqueBackground = "0xcccccc";
					flagLabel.setStyle("color","white");
					flagLabel.setStyle("fontSize","10");
					flagLabel.text = "日";
					/*word*/
					var wordText:Text = new Text();
					wordText.setStyle("color","green");
					if(jpword.word == null){
						wordText.text = jpword.kana;
					}else{
						wordText.text = jpword.word;
					}
					/*addtonewwordImg*/
					var addToNewWordBookImg:Image = new Image();
					addToNewWordBookImg.source = "img/add001_img.png";
					addToNewWordBookImg.width = 20;
					addToNewWordBookImg.height = 20;
					addToNewWordBookImg.useHandCursor = true;
					addToNewWordBookImg.mouseChildren = false;
					addToNewWordBookImg.buttonMode = true;
					addToNewWordBookImg.addEventListener(MouseEvent.ROLL_OVER,function(event:MouseEvent):void{Effects.doBlurIn([event.target]);});
					addToNewWordBookImg.addEventListener(MouseEvent.ROLL_OUT,function(event:MouseEvent):void{Effects.doBlurOut([event.target]);});
					var wlspacer:Spacer = new Spacer();
					wlspacer.percentWidth = 8;
					/*WordMeaningBox contains wordmeanings.One word to many meanings.*/
					var wordMeaningBox:VBox = new VBox();
					for(var j:int=0;j<jpword.jpwordmeanings.length;j++){
						var jpwordMeaning:Jpwordmeaning = jpword.jpwordmeanings.getItemAt(j) as Jpwordmeaning;
						var meaningLine:Text = new Text();
						meaningLine.text = jpword.jpwordmeanings.length > 1 ? "（"+int(j+1)+"）"+jpwordMeaning.meaning : jpwordMeaning.meaning;
						wordMeaningBox.addChild(meaningLine);
						for(var k:int=0;k<jpwordMeaning.jpwordexamples.length;k++){
							var jpwordExample:Jpwordexample = jpwordMeaning.jpwordexamples.getItemAt(k) as Jpwordexample;
							/*Many meanings to many examples.*/
							var exampleBox:HBox = new HBox();
							var exampleRound:Label = new Label();
							exampleRound.setStyle("fontSize","10");
							exampleRound.setStyle("color","gray");
							exampleRound.text = "●";
							var exampleTextText:Text = new Text();
							exampleTextText.text = jpwordExample.example;
							var exampleMeaningText:Text = new Text();
							exampleMeaningText.text = jpwordExample.examplemeaning;
							var exampleSeparator:Label = new Label();
							exampleSeparator.text = "/";
							exampleBox.addChild(exampleRound);
							exampleBox.addChild(exampleTextText);
							exampleBox.addChild(exampleSeparator);
							exampleBox.addChild(exampleMeaningText);
							wordMeaningBox.addChild(exampleBox);
						}
					}
					wordLine.addChild(wordText);
					wordLine.addChild(flagLabel);
					wordLine.addChild(kanaText);
					wordLine.addChild(wlspacer);
					wordLine.addChild(addToNewWordBookImg);
					wordBox.addChild(wordLine);
					wordBox.addChild(wordMeaningBox);
					this.addChild(wordBox);
			
					/*In the middle of each word has a spacer.*/
					var wordTool:HBox = new HBox();
					wordTool.percentWidth = 100;
					wordTool.height = 30;
					var perfectTheWordBtn:LinkButton = new LinkButton();
					perfectTheWordBtn.height = 18;
					perfectTheWordBtn.label = "完善词条";
					perfectTheWordBtn.setStyle("fontSize",12);
					var addToNewWordBookBtn:LinkButton = new LinkButton();
					addToNewWordBookBtn.height = 18;
					addToNewWordBookBtn.label = "加入生词本";
					addToNewWordBookBtn.setStyle("fontSize",12);
					var spacer:Spacer = new Spacer();
					spacer.percentWidth = 75;
					wordTool.addChild(spacer);
					wordTool.addChild(perfectTheWordBtn);
					wordTool.addChild(addToNewWordBookBtn);
					this.addChild(wordTool);
			
					/*Do fade effect.*/
					Effects.doFadeIn([this]);
				}
			}
		}

		public function createSearchResultDivByCnToJp(jpwordMeaningList:ArrayCollection):void{
			_jpwordList = new ArrayCollection();
			_jpwordList.addItem(2);
			_jpwordList.addItem(jpwordMeaningList);
			
			this.removeAllChildren();
		}

		public function createNoResultDiv():void{
			this.removeAllChildren();
			var noResultBox:VBox = new VBox();
			noResultBox.setStyle("fontSize","12");
			noResultBox.setStyle("color","black");
			noResultBox.name = "noResultBox";
			var noResultLabel:Label = new Label();
			noResultLabel.text = "对不起，没有找到您要的单词！";
			noResultLabel.setStyle("fontSize","24");
			noResultLabel.setStyle("color","gray");
			noResultBox.addChild(noResultLabel);
			this.addChild(noResultBox);
		}

		public function createAssociateDiv(jpwordList:ArrayCollection):void{
			_jpwordList = new ArrayCollection();
			_jpwordList.addItem(1);
			_jpwordList.addItem(jpwordList);
			
			if(this.getChildByName("noResultBox")){
				var associateBox:VBox = new VBox();
				associateBox.setStyle("paddingTop","30");
				associateBox.setStyle("fontSize","14");
				var associateLabel:Label = new Label();
				associateLabel.setStyle("fontSize","18");
				associateLabel.text = "您要找的可能是：";
				associateBox.addChild(associateLabel);
				for(var i:int=0;i<jpwordList.length;i++){
					var associateWordLine:HBox = new HBox();
					var associateWordRound:Label = new Label();
					associateWordRound.setStyle("fontSize","10");
					associateWordRound.setStyle("color","gray");
					associateWordRound.text = "●";
					var associateWordText:Text = new Text();
					associateWordText.setStyle("color","blue");
					associateWordText.setStyle("textDecoration","underline");
					associateWordText.useHandCursor = true;
					associateWordText.mouseChildren = false;
					associateWordText.buttonMode = true;
					associateWordText.id = String(i);
					associateWordText.addEventListener(MouseEvent.CLICK,function(event:MouseEvent):void{
						var tmpList:ArrayCollection = new ArrayCollection();
						tmpList.addItem(jpwordList.getItemAt(int(event.currentTarget.id)));
						try{
							Text(event.currentTarget).parentApplication["searchResultDiv"].createSearchResultDivByJpToCn(tmpList,false);
						}catch(error:Error){
							navigateToURL(new URLRequest('http://localhost:8080/'),'_blank');
						}
						
					});
					if(Jpword(jpwordList.getItemAt(i)).word == null){
						associateWordText.text = Jpword(jpwordList.getItemAt(i)).kana;
					}else{
						associateWordText.text = Jpword(jpwordList.getItemAt(i)).word;
					}
					var associateKanaText:Text = new Text();
					associateKanaText.text = "【"+Jpword(jpwordList.getItemAt(i)).kana+"】"
					associateWordLine.addChild(associateWordRound);
					associateWordLine.addChild(associateWordText);
					associateWordLine.addChild(associateKanaText);
					associateBox.addChild(associateWordLine);
				}
				this.addChild(associateBox);
			}
		}
	}
}