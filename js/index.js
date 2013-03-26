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

});

$(window).resize(function() {
  var h = Math.max($(window).height(), $(document).height()) - ($('#header').height() + $('#footer').height());
      
      $('#content').height(h);
      $('#left').height(h);
      $('#right').height(h);
      $('#center').height(h);
      
});