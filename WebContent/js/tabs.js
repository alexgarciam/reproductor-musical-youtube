/*
$(document).ready(function(){
	$(".menu > li").click(function(e){
		switch(e.target.id){
			case "tab_search":
				//change status & style menu
				$("#tab_search").addClass("active");
				$("#tab_videos").removeClass("active");
				$("#tab_rep").removeClass("active");
				$("#tab_albums").removeClass("active");
				$("#tab_artists").removeClass("active");
				$("#tab_listas").removeClass("active");			
				//display selected division, hide others
				$("div.tab_search").fadeIn();
				$("div.tab_videos").css("display", "none");
				$("div.tab_rep").css("display", "none");
				$("div.tab_albums").css("display", "none");
				$("div.tab_artists").css("display", "none");
				$("div.tab_listas").css("display", "none");
			break;
			case "tab_videos":
				//change status & style menu
				$("#tab_search").removeClass("active");
				$("#tab_videos").addClass("active");
				$("#tab_rep").removeClass("active");
				$("#tab_albums").removeClass("active");
				$("#tab_artists").removeClass("active");
				$("#tab_listas").removeClass("active");		
				//display selected division, hide others
				$("div.tab_videos").fadeIn();
				$("div.tab_search").css("display", "none");
				$("div.tab_rep").css("display", "none");
				$("div.tab_albums").css("display", "none");
				$("div.tab_artists").css("display", "none");
				$("div.tab_listas").css("display", "none");
			break;
			case "tab_rep":
				//change status & style menu
				$("#tab_search").removeClass("active");
				$("#tab_videos").removeClass("active");
				$("#tab_rep").addClass("active");
				$("#tab_albums").removeClass("active");
				$("#tab_artists").removeClass("active");
				$("#tab_listas").removeClass("active");		
				//display selected division, hide others
				$("div.tab_rep").fadeIn();
				$("div.tab_search").css("display", "none");
				$("div.tab_videos").css("display", "none");
				$("div.tab_albums").css("display", "none");
				$("div.tab_artists").css("display", "none");
				$("div.tab_listas").css("display", "none");
			break;
			case "tab_albums":
				//change status & style menu
				
				$("#tab_albums").addClass("active");
				$("#tab_search").removeClass("active");
				$("#tab_videos").removeClass("active");
				$("#tab_rep").removeClass("active");
				$("#tab_artists").removeClass("active");
				$("#tab_listas").removeClass("active");		
				//display selected division, hide others
				$("div.tab_albums").fadeIn();
				$("div.tab_rep").css("display", "none");
				$("div.tab_search").css("display", "none");
				$("div.tab_videos").css("display", "none");
				$("div.tab_artists").css("display", "none");
				$("div.tab_listas").css("display", "none");
			break;
			case "tab_artists":
				//change status & style menu				
				$("#tab_artists").addClass("active");
				$("#tab_search").removeClass("active");
				$("#tab_videos").removeClass("active");
				$("#tab_rep").removeClass("active");
				$("#tab_albums").removeClass("active");
				$("#tab_listas").removeClass("active");		
				//display selected division, hide others
				$("div.tab_artists").fadeIn();
				$("div.tab_albums").css("display", "none");
				$("div.tab_rep").css("display", "none");
				$("div.tab_search").css("display", "none");
				$("div.tab_videos").css("display", "none");
				$("div.tab_listas").css("display", "none");
				
			break;
			case "tab_listas":
				//change status & style menu	
				$("#tab_listas").addClass("active");
				$("#tab_search").removeClass("active");
				$("#tab_videos").removeClass("active");
				$("#tab_rep").removeClass("active");
				$("#tab_albums").removeClass("active");
				$("#tab_artists").removeClass("active");
				
				//display selected division, hide others
				$("div.tab_listas").fadeIn();
				$("div.tab_artists").css("display", "none");
				$("div.tab_albums").css("display", "none");
				$("div.tab_rep").css("display", "none");
				$("div.tab_search").css("display", "none");
				$("div.tab_videos").css("display", "none");
				
			break;
		}
		//alert(e.target.id);
		return false;
	});
});

*/

function showMusica(){
	//change status & style menu
	/*
	$("#tab_search").addClass("active");
	$("#tab_videos").removeClass("active");
	$("#tab_rep").removeClass("active");
	$("#tab_albums").removeClass("active");
	$("#tab_artists").removeClass("active");	
	$("#tab_listas").removeClass("active");	
	*/
	//display selected division, hide others
	$("div.tab_search").fadeIn();
	$("div.tab_videos").css("display", "none");
	$("div.tab_rep").css("display", "none");
	$("div.tab_albums").css("display", "none");
	$("div.tab_artists").css("display", "none");
	$("div.tab_listas").css("display", "none");
	$("div.div_tab_login_account").css("display", "none");
	
	$('#youtubeResults').hide();
	$('#div_tab_albums').hide();
	$('#div_tab_artists').hide();
	$('#div_tab_listas').hide();
	$('#actualRepro').hide();
	$('#div_tab_login_account').hide();
	$("#searchresults").show();
}

function showAlbums(){
	//change status & style menu
	/*
	$("#tab_search").removeClass("active");
	$("#tab_videos").removeClass("active");
	$("#tab_rep").removeClass("active");
	$("#tab_albums").addClass("active");
	$("#tab_artists").removeClass("active");
	$("#tab_listas").removeClass("active");		
	*/
	//display selected division, hide others
	$("div.tab_albums").fadeIn();
	$("div.tab_videos").css("display", "none");
	$("div.tab_rep").css("display", "none");
	$("div.tab_search").css("display", "none");
	$("div.tab_artists").css("display", "none");
	$("div.tab_listas").css("display", "none");
}


function showArtists(){
	//change status & style menu
	/*
	$("#tab_artists").addClass("active");
	$("#tab_videos").removeClass("active");
	$("#tab_rep").removeClass("active");
	$("#tab_albums").removeClass("active");
	$("#tab_search").removeClass("active");	
	$("#tab_listas").removeClass("active");		
	*/
	//display selected division, hide others
	$("div.tab_albums").fadeIn();
	$("div.tab_videos").css("display", "none");
	$("div.tab_rep").css("display", "none");
	$("div.tab_search").css("display", "none");
	$("div.tab_artists").css("display", "none");
	$("div.tab_listas").css("display", "none");
	$("div.div_tab_login_account").css("display", "none");
	$("div.div_tab_login_account").css("display", "none");
}

function showRepo(){
	//change status & style menu
	/*
	$("#tab_rep").addClass("active");
	$("#tab_videos").removeClass("active");
	$("#tab_artists").removeClass("active");
	$("#tab_albums").removeClass("active");
	$("#tab_search").removeClass("active");	
	$("#tab_listas").removeClass("active");	
	*/	
	//display selected division, hide others
	$("div.tab_rep").fadeIn();
	$("div.tab_videos").css("display", "none");
	$("div.tab_albums").css("display", "none");
	$("div.tab_search").css("display", "none");
	$("div.tab_artists").css("display", "none");
	$("div.tab_listas").css("display", "none");
	$("div.div_tab_login_account").css("display", "none");
	
	$('#youtubeResults').hide();
	$('#div_tab_albums').hide();
	$('#div_tab_artists').hide();
	$('#div_tab_listas').hide();
	$('#searchresults').hide();
	$('#div_tab_login_account').hide();
	$("#actualRepro").fadeIn();	
}


function showListas(){
	//change status & style menu
	/*
	$("#tab_listas").addClass("active");		
	$("#tab_rep").removeClass("active");
	$("#tab_videos").removeClass("active");
	$("#tab_artists").removeClass("active");
	$("#tab_albums").removeClass("active");
	$("#tab_search").removeClass("active");	
	*/
	//display selected division, hide others
	$("div.tab_listas").fadeIn();
	$("div.tab_rep").css("display", "none");	
	$("div.tab_videos").css("display", "none");
	$("div.tab_albums").css("display", "none");
	$("div.tab_search").css("display", "none");
	$("div.tab_artists").css("display", "none");
	$("div.div_tab_login_account").css("display", "none");
	
	
	$('#youtubeResults').hide();
	$('#div_tab_albums').hide();
	$('#div_tab_artists').hide();
	$('#actualRepro').hide();
	$('#searchresults').hide();
	$('#div_tab_login_account').hide();
	$("#div_tab_listas").fadeIn();	
	
}


function showLogin(){
	$("div_tab_login_account").fadeIn();
	$("div.tab_rep").css("display", "none");	
	$("div.tab_videos").css("display", "none");
	$("div.tab_albums").css("display", "none");
	$("div.tab_search").css("display", "none");
	$("div.tab_artists").css("display", "none");
	$("div.tab_listas").css("display", "none");
	
	
	$('#youtubeResults').hide();
	$('#div_tab_albums').hide();
	$('#div_tab_artists').hide();
	$('#actualRepro').hide();
	$('#searchresults').hide();
	$('#div_tab_listas').hide();
	$("#div_tab_login_account").fadeIn();	
}


