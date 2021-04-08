


async function addLikeToPhoto(photoId) {
    const element = document.querySelector(`#card-${photoId} span`);
    element.textContent = (parseInt(element.textContent)+1).toString()
    const data = await fetch(`/photos/${photoId}/like`, {
        method: "POST"
    }).then(res => res.json()).catch(() => null);
}

async function searchPhotosByName() {
    const value = document.querySelector('#search-input')?.value;
    const html = await fetch(`/photos?q=${value}`).then(res => res.text()).catch();
    const containerEl = document.querySelector(".container");
    containerEl.innerHTML = html;
}