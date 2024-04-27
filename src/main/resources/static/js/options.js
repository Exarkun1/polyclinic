function findDateOption(target) {
    let timeTarget = document.querySelector('.time_slr');
    let day = freeTimes.find(x => x.a === target.value);
    let dayTimes = day.b;
    removeOptions(timeTarget);
    addOptions(timeTarget, dayTimes);
}
function removeOptions(target) {
    let L = target.options.length - 1;
    for(let i = L; i >= 0; i--) {
        target.remove(i);
    }
}
function addOptions(target, args) {
    for (let arg of args){
        var option = document.createElement('option');
        option.value = arg;
        option.innerHTML = arg;
        target.appendChild(option);
    }
}