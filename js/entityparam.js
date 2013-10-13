$(document).ready(function() {
  $('#loader').removeClass('visibleoff').addClass('visibleon');
  $.ajax({url: "html/entityparam.html", cache: false}).done(
    function( html ) {
      $('#center').html(html);
      $('#loader').removeClass('visibleon').addClass('visibleoff');

      //Draw select
      ////#entityID
      $.ajax({url: "EntityTypeView?action=select", cache: false}).done(function(html) {$("#entityID").html(html);});

      function drawtable() {
	    $(function() {
        $.ajax({
          url: "EntityParamView?action=view",
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

        $('#entityID').val($(nTds[0]).text());
        $('#code').val($(nTds[1]).text());
        $('#sense').val($(nTds[2]).text());
        $('#mark').val($(nTds[3]).text());
        $('#num').val($(nTds[4]).text());
        $('#mask').val($(nTds[5]).text());
        $('#required').val($(nTds[6]).text());
        $('#enabled').val($(nTds[7]).text());
        $('#visible').val($(nTds[8]).text());
        $('#defaultValue').val($(nTds[9]).text());
      });

	  //////////////// Event Operation -->
      $('#ins').click(function(event) {
        var js_action       ='ins';
        var js_entityID     =$('#entityID').val();
        var js_code         =$('#code').val();
        var js_sense        =$('#sense').val();
        var js_mark         =$('#mark').val();
        var js_num          =$('#num').val();
        var js_mask         =$('#mask').val();
        var js_required     =$('#required').val();
        var js_enabled      =$('#enabled').val();
        var js_visible      =$('#visible').val();
        var js_defaultValue =$('#defaultValue').val();


        $.get('EntityParamView',
          {action:js_action,entityID:js_entityID,code:js_code,
           sense:js_sense,mark:js_mark,num:js_num,mask:js_mask,
           required:js_required,enabled:js_enabled,visible:js_visible,
           defaultValue:js_defaultValue},function(responseText) { 
          drawtable();
        });
      });

      $('#upd').click(function(event) {
        var js_action       ='upd';
        var js_entityID     =$('#entityID').val();
        var js_code         =$('#code').val();
        var js_sense        =$('#sense').val();
        var js_mark         =$('#mark').val();
        var js_num          =$('#num').val();
        var js_mask         =$('#mask').val();
        var js_required     =$('#required').val();
        var js_enabled      =$('#enabled').val();
        var js_visible      =$('#visible').val();
        var js_defaultValue =$('#defaultValue').val();
      
        $.get('EntityParamView',
           {action:js_action,entityID:js_entityID,code:js_code,
           sense:js_sense,mark:js_mark,num:js_num,mask:js_mask,
           required:js_required,enabled:js_enabled,visible:js_visible,
           defaultValue:js_defaultValue},function(responseText) { 

          drawtable();
        });
      });

      $('#delete').click(function(event) {
        var js_action       ='del';
        var js_entityID     =$('#entityID').val();
        var js_code         =$('#code').val();
     
        $.get('EntityParamView',{action:js_action,entityID:js_entityID,code:js_code},function(responseText) {
          drawtable();
        });
      });
  });
});