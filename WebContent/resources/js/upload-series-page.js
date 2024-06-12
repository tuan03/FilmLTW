flatpickr("#release-date", { enableTime: true, dateFormat: "d/m/Y H:i" })

const cancelMovieCover = (target) => {
    const docAvatarBtn = target.closest(".form-group")
    docAvatarBtn.querySelector("#movie-cover-preview").innerHTML = ""
    docAvatarBtn.querySelector("input").value = ""
    target.remove()
}

$("#movie-cover").on("change", function (e) {
    const target = e.target
    const file = target.files[0]

    if (file) {
        const previewImage = document.createElement("img")
        previewImage.classList.add("movie-cover-preview")
        previewImage.setAttribute("alt", "Ảnh bìa phim")

        const imageURL = URL.createObjectURL(file)
        previewImage.setAttribute("src", imageURL)
        previewImage.onload = function () {
            URL.revokeObjectURL(imageURL)
        }

        const cancelDocAvatarBtn = document.createElement("div")
        cancelDocAvatarBtn.classList.add("cancel-movie-cover-btn")
        cancelDocAvatarBtn.innerHTML = `<span><i class="bi bi-x-circle-fill"></i></span>`
        cancelDocAvatarBtn.setAttribute("onclick", "cancelMovieCover(this)")
        cancelDocAvatarBtn.setAttribute("title", "Hủy thêm ảnh này")

        const docAvatarBtn = document.getElementById("movie-cover-preview")
        docAvatarBtn.appendChild(previewImage)
        docAvatarBtn.appendChild(cancelDocAvatarBtn)
    }
})

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
    const items = document.querySelectorAll(
        ".form-group.movie-genre .select-genres-box .dropdown-item"
    )
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

$("#upload-video-form").on("submit", async function (e) {
    e.preventDefault()
    const form = e.target
    const formData = new FormData(form)

    const cover = formData.get("movie-cover")
    const title = formData.get("movie-title")
    const releaseDate = formData.get("release-date")
    const description = formData.get("movie-description")
    console.log(">>> data >>>", { cover, title, releaseDate, description, genresPicked })

    let valid = true

    if (!cover || !cover.name) {
        valid = false
        $(this).find(".form-group.movie-cover .invalid-message").attr("hidden", false)
    } else {
        $(this).find(".form-group.movie-cover .invalid-message").attr("hidden", true)
    }
    if (!title) {
        valid = false
        $(this).find(".form-group.movie-title .invalid-message").attr("hidden", false)
    } else {
        $(this).find(".form-group.movie-title .invalid-message").attr("hidden", true)
    }
    if (!releaseDate) {
        valid = false
        $(this).find(".form-group.release-date .invalid-message").attr("hidden", false)
    } else {
        $(this).find(".form-group.release-date .invalid-message").attr("hidden", true)
    }
    if (!description) {
        valid = false
        $(this).find(".form-group.movie-description .invalid-message").attr("hidden", false)
    } else {
        $(this).find(".form-group.movie-description .invalid-message").attr("hidden", true)
    }
    if (genresPicked.values.length === 0) {
        valid = false
        $(this).find(".form-group.movie-genre .invalid-message").attr("hidden", false)
    } else {
        $(this).find(".form-group.movie-genre .invalid-message").attr("hidden", true)
    }

    if (valid) {
        form.submit()
    } else {
        toastr.error("Không thể đăng tải phim, vui lòng kiểm tra lại!")
    }
})
