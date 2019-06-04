function numberOfLetters(gameId) {
    var userGuesses = document.getElementsByClassName("userGuesses" + gameId);
    var computerWord = document.getElementById("computerWord" + gameId);
    for (var i = 0; i < userGuesses.length; i++) {
        var occurrences = 0
        for (var j = 0; j < 5; j++) {
            for (var k = 0; k < 5; k++) {
                if (userGuesses[i].innerHTML[j] === computerWord[k]) occurrences++;
            }
        }
        userGuesses[i].innerHTML += '(' + occurrences + ')'
    }
    var computerGuesses = document.getElementsByClassName("computerGuesses");
    var userWord = document.getElementById("userWord" + gameId);
    for (var i = 0; i < computerGuesses.length; i++) {
        var occurrences = 0
        for (var j = 0; j < 5; j++) {
            for (k = 0; k < 5; k++) {
                if (computerGuesses[i].innerHTML[j] === userWord[k]) occurrences++;
            }
        }
        computerGuesses[i].innerHTML += '(' + occurrences + ')'
    }
}