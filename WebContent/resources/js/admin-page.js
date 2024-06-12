const series_dataset = [
    {
        title: "Myki Yo",
        episodes_count: 11,
        release_date: "11/12/2024 12:34",
        description:
            "Một thông điệp từ cựu đội trưởng đã qua đời (Pantoliano) đã đẩy Mike (Smith) và Marcus (Lawrence) vào cuộc truy lùng các quan chức tham nhũng. Phần 4 này sẽ là những diễn biến đầy gay cấn của thám tử Miami-Dade Mike Lowrey (Will Smith) và Marcus Burnett (Martin Lawrence). Giờ đây, Mike và Marcus phải hợp tác để tiêu diệt tên Đại úy Conrad Howard, người đang bị buộc tội hoạt động cùng băng đảng ma túy.",
    },
    {
        title: "Nanta",
        episodes_count: 11,
        release_date: "11/12/2024 12:34",
        description:
            "Một thông điệp từ cựu đội trưởng đã qua đời (Pantoliano) đã đẩy Mike (Smith) và Marcus (Lawrence) vào cuộc truy lùng các quan chức tham nhũng. Phần 4 này sẽ là những diễn biến đầy gay cấn của thám tử Miami-Dade Mike Lowrey (Will Smith) và Marcus Burnett (Martin Lawrence). Giờ đây, Mike và Marcus phải hợp tác để tiêu diệt tên Đại úy Conrad Howard, người đang bị buộc tội hoạt động cùng băng đảng ma túy.",
    },
    {
        title: "Faye Yoko",
        episodes_count: 11,
        release_date: "11/12/2024 12:34",
        description:
            "Một thông điệp từ cựu đội trưởng đã qua đời (Pantoliano) đã đẩy Mike (Smith) và Marcus (Lawrence) vào cuộc truy lùng các quan chức tham nhũng. Phần 4 này sẽ là những diễn biến đầy gay cấn của thám tử Miami-Dade Mike Lowrey (Will Smith) và Marcus Burnett (Martin Lawrence). Giờ đây, Mike và Marcus phải hợp tác để tiêu diệt tên Đại úy Conrad Howard, người đang bị buộc tội hoạt động cùng băng đảng ma túy.",
    },
    {
        title: "Freen Becky",
        episodes_count: 11,
        release_date: "11/12/2024 12:34",
        description:
            "Một thông điệp từ cựu đội trưởng đã qua đời (Pantoliano) đã đẩy Mike (Smith) và Marcus (Lawrence) vào cuộc truy lùng các quan chức tham nhũng. Phần 4 này sẽ là những diễn biến đầy gay cấn của thám tử Miami-Dade Mike Lowrey (Will Smith) và Marcus Burnett (Martin Lawrence). Giờ đây, Mike và Marcus phải hợp tác để tiêu diệt tên Đại úy Conrad Howard, người đang bị buộc tội hoạt động cùng băng đảng ma túy.",
    },
    {
        title: "On sss",
        episodes_count: 11,
        release_date: "11/12/2024 12:34",
        description:
            "Một thông điệp từ cựu đội trưởng đã qua đời (Pantoliano) đã đẩy Mike (Smith) và Marcus (Lawrence) vào cuộc truy lùng các quan chức tham nhũng. Phần 4 này sẽ là những diễn biến đầy gay cấn của thám tử Miami-Dade Mike Lowrey (Will Smith) và Marcus Burnett (Martin Lawrence). Giờ đây, Mike và Marcus phải hợp tác để tiêu diệt tên Đại úy Conrad Howard, người đang bị buộc tội hoạt động cùng băng đảng ma túy.",
    },
    {
        title: "Potrit Lady",
        episodes_count: 11,
        release_date: "11/12/2024 12:34",
        description:
            "Một thông điệp từ cựu đội trưởng đã qua đời (Pantoliano) đã đẩy Mike (Smith) và Marcus (Lawrence) vào cuộc truy lùng các quan chức tham nhũng. Phần 4 này sẽ là những diễn biến đầy gay cấn của thám tử Miami-Dade Mike Lowrey (Will Smith) và Marcus Burnett (Martin Lawrence). Giờ đây, Mike và Marcus phải hợp tác để tiêu diệt tên Đại úy Conrad Howard, người đang bị buộc tội hoạt động cùng băng đảng ma túy.",
    },
]

function debounce(func, timeout = 300) {
    let timer
    return (...args) => {
        clearTimeout(timer)
        timer = setTimeout(() => {
            func.apply(this, args)
        }, timeout)
    }
}

const renderSeries = (series_list) => {
    const result_table = document.getElementById("search-series-result-table")
    result_table.innerHTML = `
        <tr>
            <th>Tên phim</th>
            <th>Mô tả</th>
            <th>Ngày công chiếu</th>
            <th></th>
        </tr>`
    for (const series of series_list) {
        const row = document.createElement("tr")
        row.innerHTML = `
            <td class="series-title">${series.title}</td>
            <td>${series.episodes_count}</td>
            <td>${series.release_date}</td>
            <td>
                <div class="actions">
                    <button class="delete-btn" data-bs-toggle="tooltip" data-bs-placement="bottom"
                        title="Xóa phim">
                        <i class="bi bi-trash3"></i>
                    </button>
                    <button class="edit-btn" data-bs-toggle="tooltip" data-bs-placement="bottom"
                        title="Chỉnh sửa phim">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                </div>
            </td>`
        result_table.appendChild(row)
    }
}

const filterSeries = debounce((value) => {
    if (value && value.length > 0) {
        const result = series_dataset.filter(({ title }) => title.includes(value.toLowerCase()))
        renderSeries(result)
    } else {
        renderSeries(series_dataset)
    }
}, 300)

$("#search-series-input").on("input", function (e) {
    const input = e.target
    const value = input.value
    filterSeries(value)
})

const startEditGenre = (target) => {
    target.nextElementSibling.hidden = false
}
const blurEditGenre = (target) => {
    target.nextElementSibling.hidden = true
}

$("#add-genre-btn").on("click", function (e) {
    const newGenreExist = $("#new-genre-input")
    if (newGenreExist.length === 0) {
        $(".genres").prepend(`
        <form action="genre/add.htm" method="post" id="new-genre-form">
        <div class="genre">
            <input class="editor" type="text" name="content" id="new-genre-input" placeholder="Nhập thể loại..." onblur="blurEditGenre(this)"
                onfocus="startEditGenre(this)" data-genre-id="1" data-genre-value="Hành động" />
        </div></form>`)
        $("#new-genre-input").focus()

        
    }
})
