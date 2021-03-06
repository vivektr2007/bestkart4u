/*
   Deluxe Menu Data File
   Created by Deluxe Tuner v3.10
   http://deluxe-menu.com
*/



//--- Common
var menuIdentifier="blueMenu";
var isHorizontal=1;
var smColumns=1;
var smOrientation=0;
var dmRTL=0;
var pressedItem=-2;
var itemCursor="default";
var itemTarget="_self";
var statusString="link";
var blankImage="";
var pathPrefix_img="";
var pathPrefix_link="";

//--- Dimensions
var menuWidth="500px";
var menuHeight="30px";
var smWidth="";
var smHeight="";

//--- Positioning
var absolutePos=0;
var posX="0px";
var posY="0px";
var topDX=0;
var topDY=-1;
var DX=0;
var DY=0;
var subMenuAlign="left";
var subMenuVAlign="top";

//--- Font
var fontStyle=["normal 11px Lucida Sans Unicode,Lucida Grande,Verdana,Arial,Helvetica,sans-serif","normal 11px Lucida Sans Unicode,Lucida Grande,Verdana,Arial,Helvetica,sans-serif"];
var fontColor=["#FFFFFF","#FFFFFF"];
var fontDecoration=["none","none"];
var fontColorDisabled="#AAAAAA";

//--- Appearance
var menuBackColor="#062540";
var menuBackImage="";
var menuBackRepeat="repeat";
var menuBorderColor="#999999";
var menuBorderWidth="0px";
var menuBorderStyle="solid";
var smFrameImage="";
var smFrameWidth="";

//--- Item Appearance
var itemBackColor=["#062540","#1B3962"];
var itemBackImage=["",""];
var itemSlideBack=0;
var beforeItemImage=["",""];
var afterItemImage=["",""];
var beforeItemImageW="";
var afterItemImageW="";
var beforeItemImageH="";
var afterItemImageH="";
var itemBorderWidth="0px";
var itemBorderColor=["#E6E6E6","#E6E6E6"];
var itemBorderStyle=["solid","none"];
var itemSpacing=0;
var itemPadding="5px 10px";
var itemAlignTop="left";
var itemAlign="left";

//--- Icons
var iconTopWidth=24;
var iconTopHeight=24;
var iconWidth=16;
var iconHeight=16;
var arrowWidth=10;
var arrowHeight=10;
var arrowImageMain=["",""];
var arrowWidthSub=15;
var arrowHeightSub=13;
var arrowImageSub=["",""];

//--- Separators
var separatorImage="";
var separatorWidth="100%";
var separatorHeight="3px";
var separatorAlignment="left";
var separatorVImage="";
var separatorVWidth="2px";
var separatorVHeight="100%";
var separatorPadding="0px";

//--- Floatable Menu
var floatable=0;
var floatIterations=6;
var floatableX=1;
var floatableY=1;
var floatableDX=15;
var floatableDY=15;

//--- Movable Menu
var movable=0;
var moveWidth=12;
var moveHeight=20;
var moveColor="#AA0000";
var moveImage="";
var moveCursor="default";
var smMovable=0;
var closeBtnW=15;
var closeBtnH=15;
var closeBtn="";

//--- Transitional Effects & Filters
var transparency="90";
var transition=101;
var transOptions="";
var transDuration=300;
var transDuration2=200;
var shadowLen=0;
var shadowColor="#777777";
var shadowTop=1;

//--- CSS Support (CSS-based Menu)
var cssStyle=0;
var cssSubmenu="";
var cssItem=["",""];
var cssItemText=["",""];

//--- Advanced
var dmObjectsCheck=0;
var saveNavigationPath=1;
var showByClick=0;
var noWrap=1;
var smShowPause=200;
var smHidePause=1000;
var smSmartScroll=1;
var topSmartScroll=0;
var smHideOnClick=1;
var dm_writeAll=0;
var useIFRAME=0;
var dmSearch=0;

//--- AJAX-like Technology
var dmAJAX=0;
var dmAJAXCount=0;
var ajaxReload=0;

//--- Dynamic Menu
var dynamic=0;

//--- Popup Menu
var popupMode=0;

//--- Keystrokes Support
var keystrokes=0;
var dm_focus=1;
var dm_actKey=113;

//--- Sound
var onOverSnd="";
var onClickSnd="";

var itemStyles = [
    ["itemHeight=33px","itemBackImage=blue-menu.files/normal_back.png,blue-menu.files/hover_back.png","fontStyle='normal 13px Lucida Sans Unicode,Lucida Grande,Verdana,Arial,Helvetica,sans-serif','normal 13px Lucida Sans Unicode,Lucida Grande,Verdana,Arial,Helvetica,sans-serif'"],
    ["itemHeight=4px","itemBackColor=#062540,#062540","itemBackImage=blue-menu.files/red-bottom-line.gif,blue-menu.files/red-bottom-line.gif","fontStyle='normal 1px Arial','normal 1px Arial'","fontDecoration=none,none","itemAddStyle=background-position: bottom left; background-repeat: repeat-x;"],
];
var menuStyles = [
    ["smColumns=2"],
    ["smColumns=3"],
];
dm_init();