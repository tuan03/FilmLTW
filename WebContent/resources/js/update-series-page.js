const genresPicked = { values: [] };

function addChip(genreLabel, genreId) {
    const genreExist = genresPicked.values.includes(genreId);
    if (genreExist) return;

    genresPicked.values.push(genreId);

    const chip = document.createElement("div");
    chip.classList.add("chip", "btn", "btn-outline-primary", "me-2");
    chip.textContent = genreLabel;

    const closeButton = document.createElement("i");
    closeButton.classList.add("bi", "bi-x-lg");
    closeButton.addEventListener("click", function () {
        chip.remove();
        genresPicked.values = genresPicked.values.filter(
            (genre_id) => genre_id !== genreId
        );
    });

    chip.appendChild(closeButton);
    document.getElementById("chips-container").appendChild(chip);
}
const setUpSelectGenres = () => {
    const items = document.querySelectorAll(
        "#update-series-form .select-genres-box .dropdown-item"
    );
    for (const item of items) {
        item.addEventListener("click", function (e) {
            e.preventDefault();
            const genreLabel = e.target.getAttribute("data-genre-label");
            const genreId = e.target.getAttribute("data-genre-id");
            addChip(genreLabel, genreId);
        });
    }
};
setUpSelectGenres();

const addGenres = (target) => {
    const type = target.getAttribute("data-type-is-add");
    if (type === "true") {
        target.innerHTML = `
            <i class="bi bi-check-circle-fill"></i>
            <span>Xong</span>`;
        target.setAttribute("data-type-is-add", "false");
        const detais = target.closest(".details");
        detais.querySelector(".select-genres-box").hidden = false;
    } else {
        target.innerHTML = `
            <i class="bi bi-plus-circle-fill"></i>
            <span>Thêm</span>`;
        target.setAttribute("data-type-is-add", "true");
        const detais = target.closest(".details");
        detais.querySelector(".select-genres-box").hidden = true;
    }
};

$("#update-series-form").on("submit", function (e) {
    e.preventDefault();
    const form = e.target;
    const formData = new FormData(form);

    const title = formData.get("title");
    const description = formData.get("description");
    console.log(">>> data before send to BE >>>", {
        title,
        description,
        genresPicked,
    });

    let valid = true;


   
        form.submit();
    
});

const uploadNewEpisodeBox = document.querySelector(
    ".upload-new-episode-section .upload-new-episode-box"
);

const renderProgressBar_failToUploadFile = ({ fileName }) => {
    const uploadProgressContent_htmlString = `
        <div class="upload-progress-details progress-fail" style="color: red;">
            <i class="bi bi-exclamation-triangle-fill" style="color: red;"></i>
            <span class="upload-progress-name">${fileName} • <strong>Tải lên thất bại</strong></span>
        </div>`;

    uploadNewEpisodeBox.innerHTML = uploadProgressContent_htmlString;
};

const renderProgressBar_fileUploaded = ({ fileName, fileLoaded, fileSize }) => {
    const uploadProgressContent_htmlString = `
        <div class="progress-box">
            <div class="upload-progress-details">
                <div style="display: flex; align-items: center; column-gap: 5px;">
                    ${fileSize
            ? `
                            <i class="bi bi-check-circle-fill" style="color: green;"></i>
                            <div class="details">
                                <span class="name">${fileName} • <strong>(Đã tải lên)</strong></span>
                                <span class="size">${fileSize}</span>
                            </div>`
            : `
                            <i class="bi bi-cloud-arrow-up-fill"></i>
                            <span class="upload-progress-name">${fileName} • <strong>Đang tải lên</strong></span>`
        }
                </div>
                <span class="upload-progress-percent">${fileLoaded}%</span>
            </div>
            <div class="upload-progress-bar">
                <div class="upload-progress-animate" style="width: ${fileLoaded}%"></div>
            </div>
        </div> `;

    uploadNewEpisodeBox.innerHTML = uploadProgressContent_htmlString;
};

const uploadFileOnDone = () => { };

const MAX_CHARS_OF_FILE_NAME = 100;

const createFormAfterUploading = ({ videoURL }) => {
    const formAfterUploading = document.getElementById("episode-info-form-after-upload")
    formAfterUploading.querySelector(".form-group.new-episode-url input").value = videoURL
    formAfterUploading.hidden = false
}

const uploadFileHandler = (file) => {
    let fileName = file.name;
    if (fileName.length >= MAX_CHARS_OF_FILE_NAME) {
        const splitName = fileName.split(".");
        fileName =
            splitName[0].substring(0, MAX_CHARS_OF_FILE_NAME + 1) +
            "... ." +
            splitName[1];
    }

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "upload/upload.htm", true);
    xhr.upload.addEventListener("progress", ({ loaded, total }) => {
        const fileLoaded = Math.floor((loaded / total) * 100);
        const fileTotal = Math.floor(total / 1000);
        let fileSize;
        fileTotal < 1024
            ? (fileSize = fileTotal + " KB")
            : (fileSize = (loaded / (1024 * 1024)).toFixed(2) + " MB");
        renderProgressBar_fileUploaded({ fileLoaded, fileName });
        if (loaded == total) {
            renderProgressBar_fileUploaded({
                fileName,
                fileSize,
                fileLoaded: 100,
            });
        }
    });
    xhr.addEventListener("load", () => {
        const status = xhr.status;
        if (status >= 200 && status < 300) {
            const { error, result } = JSON.parse(xhr.responseText)
            if (result) {
                toastr.success("Đã tải tập mới lên thành công!")
                createFormAfterUploading({ videoURL: result })
            } else {
                toastr.error(error)
            }
        } else {
            renderProgressBar_failToUploadFile({ fileName })
            toastr.error("Không thể tập mới lên!")
        }
    });

    const data = new FormData();
    data.append("file", file);

    xhr.send(data);
};

$("#upload-new-episode-input").on("change", function (e) {
    const file = e.target.files[0];
    uploadFileHandler(file);
});

const updateEpisode = (e) => {
    e.preventDefault();
    const form = e.target;
    const formData = new FormData(form);

    const newTitle = formData.get("new-title");

    let valid = true;

    if (!newTitle) {
        valid = false;
    }

    if (valid) {
        form.submit();
    } else {
        toastr.warning("Không thể chỉnh sửa tập phim");
    }
};

$("#episode-info-form-after-upload").on("submit", function (e) {
    e.preventDefault()

    const form = e.target
    const formData = new FormData(form)

    const urlVideo = formData.get("urlVideo")
    const title = formData.get("title")
    const numberEp = formData.get("numberEp")
    console.log(">>> data before send to BE >>>", { urlVideo, title, numberEp })

    let valid = true

    if (!title) {
        valid = false
        $(this).find(".form-group.new-episode-title .invalid-message").attr("hidden", false)
    } else {
        $(this).find(".form-group.new-episode-title .invalid-message").attr("hidden", true)
    }
    if (!numberEp) {
        valid = false
        $(this).find(".form-group.new-episode-ep-number .invalid-message").attr("hidden", false)
    } else {
        $(this).find(".form-group.new-episode-ep-number .invalid-message").attr("hidden", true)
    }

    if (valid) {
        form.submit()
    } else {
        toastr.error("Không thể cập nhật thông tin!")
    }
})
