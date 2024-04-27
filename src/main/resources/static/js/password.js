function showPassword(target){
	var input = target.parentElement.querySelector('.password');
	if (input.getAttribute('type') === 'password') {
		target.classList.add('visible');
		input.setAttribute('type', 'text');
	} else {
		target.classList.remove('visible');
		input.setAttribute('type', 'password');
	}
}