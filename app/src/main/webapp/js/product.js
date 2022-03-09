const tabsBtn = document.getElementsByClassName("tab");

let selectedTab = document.querySelector(".tabs-list .active");

for (let i = 0; i < tabsBtn.length; i++) {
	const tab = tabsBtn[i];

	tab.addEventListener("click", (e) => {
		e.preventDefault();

		selectedTab.classList.remove("active");
		document.getElementById(selectedTab.getAttribute("data-tab-name")).classList.remove("active");

		tab.classList.add("active");
		document.getElementById(tab.getAttribute("data-tab-name")).classList.add("active");

		selectedTab = tab;
	});
}

const stars = document.querySelectorAll(".rating span a");

let lastActive;
for (let i = 0; i < stars.length; i++) {
	const star = stars[i];
	star.addEventListener("click", (e) => {
		e.preventDefault();

		if (lastActive == star) {
			lastActive.classList.remove("active");
			lastActive = null;
			selectRating(0);
			return;
		}
		if (lastActive) lastActive.classList.remove("active");
		star.classList.add("active");

		selectRating(star.getAttribute("data-value")); // car flex inverted
		lastActive = star;
	});
}

const ratingSelect = document.getElementById("rating-input");
function selectRating(rating) {
	if (isNaN(rating)) rating = 0;

	ratingSelect.value = rating;
}
