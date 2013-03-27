/**
 * @author yla
 */

$(document).ready(function () {
  var h = Math.max($(window).height(), $(document).height()) - ($('#header').height() + $('#footer').height());      
  $('#content').height(h);
  $('#left').height(h);
  $('#right').height(h);
  $('#center').height(h);
    
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

  $("#left").hoverIntent(
    function () {
      $(this).animate({width: "250px",opacity: 0.8}, 500);      
      $("#center").animate({marginLeft: "250px",marginRight: "0px"}, 500);
      $("#right").animate({width: "0px",opacity: 0.0}, 500);
      $("#menu").animate({opacity: 0.9}, 300);
      $("#menu").slideDown();      
    },
    function () {
      $("#menu").slideUp(0);
      $(this).animate({width: "50px",opacity: 0.2}, 200 );      
      $("#center").animate({marginLeft: "50px",marginRight: "200px"}, 200 );      
      $("#right").animate({width: "200px",opacity: 0.8}, 200 );
      $("#menu").animate({opacity: 0.0}, 300);
      
    }
  );

  $("#menu").accordion({
    navigation: true
  });
  $("#menu li a").click(function(){
    $.cookie("openItem", $(this).attr("href"));
  });
  $("#menu li a[href$='" + $.cookie("openItem") + "']").addClass("open");
  
});


