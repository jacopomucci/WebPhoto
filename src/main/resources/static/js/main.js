


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

async function onInputChange(value) {
    const data = await fetch(`/ajax/photos?q=${value}`).then(res => res.json()).catch(() => []);
    const inputDropdownEl = document.querySelector(".app-dropdown");
    if (data.length) inputDropdownEl.style.display = "block";
    const dropdownElements = data.map(d => {
        return `<div 
                    class="dropdown-item" 
                    onclick="location.href='/photo/${d.id}';"
                    >${d.name}
                </div>`
    }).join("\n")
    inputDropdownEl.innerHTML = dropdownElements;
}