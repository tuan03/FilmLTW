const genresPicked = { values: [] }

function addChip(genreLabel, genreId) {
    const genreExist = genresPicked.values.includes(genreId)
    if (genreExist) return

    genresPicked.values.push(genreId)

    const chip = document.createElement("div")
    chip.classList.add("chip", "btn", "btn-outline-primary", "me-2")
    chip.textContent = genreLabel

    const closeButton = document.createElement("i")
    closeButton.classList.add("bi", "bi-x-lg")
    closeButton.addEventListener("click", function () {
        chip.remove()
        genresPicked.values = genresPicked.values.filter((genre_id) => genre_id !== genreId)
    })

    chip.appendChild(closeButton)
    document.getElementById("chips-container").appendChild(chip)
}
const setUpSelectGenres = () => {
    const items = document.querySelectorAll("#update-series-form .select-genres-box .dropdown-item")
    for (const item of items) {
        item.addEventListener("click", function (e) {
            e.preventDefault()
            const genreLabel = e.target.getAttribute("data-genre-label")
            const genreId = e.target.getAttribute("data-genre-id")
            addChip(genreLabel, genreId)
        })
    }
}
setUpSelectGenres()

const addGenres = (target) => {
    const type = target.getAttribute("data-type-is-add")
    if (type === "true") {
        target.innerHTML = `
            <i class="bi bi-check-circle-fill"></i>
            <span>Xong</span>`
        target.setAttribute("data-type-is-add", "false")
        const detais = target.closest(".details")
        detais.querySelector(".select-genres-box").hidden = false
    } else {
        target.innerHTML = `
            <i class="bi bi-plus-circle-fill"></i>
            <span>Thêm</span>`
        target.setAttribute("data-type-is-add", "true")
        const detais = target.closest(".details")
        detais.querySelector(".select-genres-box").hidden = true
    }
}

$("#update-series-form").on("submit", function (e) {
    e.preventDefault()
    const form = e.target
    const formData = new FormData(form)

    const title = formData.get("series-title")
    const description = formData.get("series-description")
    console.log(">>> data before send to BE >>>", { title, description, genresPicked })

    if (!title) {
        $(this).find('')
    }
})
