/**
 * @author yla
 */

$(document).ready(function () {
  
  var h = Math.max($(window).height(), $(document).height()) - ($('#header').height() + $('#footer').height());
      
      $('#content').height(h);
      $('#left').height(h);
      $('#right').height(h);
      $('#center').height(h);
      
      $('#menu').height($('#left').height()).width($('#left').width()+20);
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
    var h = Math.max($(window).height(), $(document).height()) - ($('#header').height() + $('#footer').height());      
      $('#content').height(h);
      $('#left').height(h);
      $('#right').height(h);
      $('#center').height(h);      
  });

  $("#menu").hoverIntent(
    function () {
      $(this).animate($(this).width(250), 500);
      //$(this).animate({right: "250px",opacity: 0.8}, 500);      
      //$("#center").animate({marginLeft: "250px",marginRight: "0px"}, 500);
      //$("#right").animate({width: "0px",opacity: 0.0}, 500);      
    },
    function () {
      $(this).animate({right: "50px",opacity: 0.2}, 200 );      
      //$("#center").animate({marginLeft: "50px",marginRight: "200px"}, 200 );      
      //$("#right").animate({width: "200px",opacity: 0.8}, 200 );
    }
  );

});


