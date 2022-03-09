const toggleBtn = document.getElementById("coupon-toggle");
const windowCoupon = document.getElementById("window-coupon");

toggleBtn.addEventListener("click", (e) => {
	e.preventDefault();

	if (windowCoupon) {
		windowCoupon.classList.toggle("active");
	}
});
