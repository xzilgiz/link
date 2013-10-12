/**
 * @author yla
 */

$(document).ready(function () {
  var bh = Math.max($(window).height(), $(document).height())
  var h = bh - ($('#header').height() + $('#footer').height());

  $('#content').height(h);
  $('#left').height(h);
  $('#right').height(h);
  $('#center').height(h);
      
      //$('#menu').height($('#left').height()).width($('#left').width()+20);
      //$('#menu').css("left",$('#content').css("left")); 
      //$('#menu').css("top","0");
      
      //= $('div #left').position();
      
      
  
  
  //$('#loader').removeClass('visibleoff').addClass('visibleon');
  //$.ajax({url: "html/body.html", cache: false}).done(
  //  function( html ) {
  //    $('#body').append(html);
  //    $('#loader').removeClass('visibleon').addClass('visibleoff');      
  //  });

  $(window).resize(function() {
    var bh = Math.max($(window).height(), $(document).height())
    var h = bh - ($('#header').height() + $('#footer').height());
          
    $('#content').height(h);
    $('#left').height(h);
    $('#right').height(h);
    $('#center').height(h);
  });

  //$("#left").hoverIntent(
    //function () {
      //$(this).animate({width: "400px",opacity: 0.8}, 400 );
      //$("#center").animate({marginLeft: "250px",marginRight: "0px"}, 500);
      //$("#right").animate({width: "0px",opacity: 0.0}, 500);      
    //},
    //function () {
      //$(this).animate({width: "200px",opacity: 0.3}, 400 );      
      //$("#center").animate({marginLeft: "50px",marginRight: "200px"}, 200 );      
      //$("#right").animate({width: "200px",opacity: 0.8}, 200 );
    //}
  //);

   $.getScript("js/login.js");

   $('#btentitytype').click(function(event) {
     $.getScript("js/entitytype.js");
   });
   
   $('#btclasstype').click(function(event) {
     $.getScript("js/classtype.js");
   });

   $('#btentityparam').click(function(event) {
     $.getScript("js/entityparam.js");
   });
});


