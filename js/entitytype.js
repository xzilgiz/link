$(document).ready(function() {
  $('#loader').removeClass('visibleoff').addClass('visibleon');
  $.ajax({url: "html/entitytype.html", cache: false}).done(
    function( html ) {
      $('#center').html(html);
      $('#loader').removeClass('visibleon').addClass('visibleoff');

      function drawtable() {
	    $(function() {
        $.ajax({
          url: "EntityTypeView?action=view",
          dataType: "json"
        }).done(function(data) {
          console.log(data);
	      $(".slimtable-paging-div").remove();
	      $(".slimtable-sprites").remove();
	      $(".slimtable-sortboth").remove();
	    
	      $("#exampletable").slimtable({
            tableData: data,
            itemsPerPage: 30
          });
        }).fail(function(param1,param2) {
          console.log("error: "+param2);
        });
      });
	  }

      drawtable();

      $('#exampletable tbody').on('click','tr', function () {
        var nTds = $('td', this);

        $('#code').val($(nTds[0]).text());
        $('#sense').val($(nTds[1]).text());
        $('#required').val($(nTds[2]).text());
      });

	  //////////////// Event Operation -->
      $('#ins').click(function(event) {
        var js_action='ins';
        var js_code=$('#code').val();
        var js_sense=$('#sense').val();
        var js_required=$('#required').val();

        $.get('EntityTypeView',{action:js_action,code:js_code,sense:js_sense,required:js_required},function(responseText) { 
          drawtable();
        });
      });

      $('#upd').click(function(event) {
        var js_action='upd';
        var js_code=$('#code').val();
        var js_sense=$('#sense').val();
        var js_required=$('#required').val();
      
        $.get('EntityTypeView',{action:js_action,code:js_code,sense:js_sense,required:js_required},function(responseText) { 
          drawtable();
        });
      });

      $('#delete').click(function(event) {
        var js_action='del';
        var js_code=$('#code').val();
        var js_sense=$('#sense').val();
        var js_required=$('#required').val();
     
        $.get('EntityTypeView',{action:js_action,code:js_code,sense:js_sense,required:js_required},function(responseText) { 
          $('#code').val('');
          $('#sense').val('');
          $('#required').val('');
          drawtable();
        });
      });
  });
});