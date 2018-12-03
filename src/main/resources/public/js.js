gridGames();




//Show game grid or one game
function  gridGames() {
    var arr = document.getElementsByClassName('item');
    if (arr.length > 1) {
        var block = document.getElementById('blockMiddle');
        block.classList.add('gridAll');
    }
    else {
        var block = document.getElementById('blockMiddle');
            block.className = 'gridOne';
            arr[0].className = 'itemAlone';
        }
    }


