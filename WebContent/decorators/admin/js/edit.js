  var getCoordInDocumentExample = function(){
	var left;
	var top;
    var draw = document.getElementsByName("draw").style;
    coords.onmousedown = function(e){
      var pointer = getCoordInDocument(e);
      var coord = document.getElementById("point");
      left = pointer.x;
      top = pointer.y;
      draw.left = pointer.x + "px";
      draw.top = pointer.y + "px";
    }
    coords.onmousemove = function(e){
      var pointer = getCoordInDocument(e);
      var coord = document.getElementById("coord");
      coord.innerHTML = "X="+pointer.x+" Y="+pointer.y;
    }
    coords.onmouseup = function(e){
      var pointer = getCoordInDocument(e);
      var coord = document.getElementById("point");
      coord.innerHTML = "X="+pointer.x+" Y="+pointer.y;
      draw.width = (pointer.x - left) + "px";
      draw.height = (pointer.y - top) + "px";
     
    }
  }
  var getCoordInDocument = function(e) {
    e = e || window.event;
    var x = e.pageX || (e.clientX +
      (document.documentElement.scrollLeft
      || document.body.scrollLeft));
    var y= e.pageY || (e.clientY +
      (document.documentElement.scrollTop
      || document.body.scrollTop));
    return {'x':x,'y':y};
  }
  window.onload = function(){
     getCoordInDocumentExample();
 };