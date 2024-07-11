const SOURCE_COLOR = '#d3869b'
const DESTINATION_COLOR = '#7daea3'
const DEFAULT_COLOR = '#d4be98'
const REPLACEMENTS = {'ą': 'a', 'ę': 'e', 'ł': 'l', 'ó': 'o', 'ś': 's'};

document.addEventListener('DOMContentLoaded', function() {
    const voivodeshipInput = document.getElementById('voivodeshipInput');
    voivodeshipInput.addEventListener('keydown', function (event) {
        if (event.key === 'Enter') {
            highlight(voivodeshipInput.value, DEFAULT_COLOR);
            voivodeshipInput.value = '';
        }
    });
});

function highlight(voivodeshipName, color) {
    voivodeshipName = replacePolishCharacters(voivodeshipName.toLowerCase());
    const voivodeship = document.querySelector(`path[id="${voivodeshipName}"]`);
    if (voivodeship.style.fill === '') voivodeship.style.fill = color;
}

function replacePolishCharacters(text) {
    return text.replace(/[ąęłóś]/g, function(match) {
        return REPLACEMENTS[match];
    });
}