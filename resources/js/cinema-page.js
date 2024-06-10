const player = new Plyr("#video-player", {
    ratio: "16:9",
    seekTime: 5,
    controls: [
        "play-large", // The large play button in the center
        "restart", // Restart playback
        "rewind", // Rewind by the seek time (default 10 seconds)
        "play", // Play/pause playback
        "fast-forward", // Fast forward by the seek time (default 10 seconds)
        "progress", // The progress bar and scrubber for playback and buffering
        "current-time", // The current time of playback
        "duration", // The full duration of the media
        "mute", // Toggle mute
        "volume", // Volume control
        "captions", // Toggle captions
        "settings", // Settings menu
        "airplay", // Airplay (currently Safari only)
        "fullscreen", // Toggle fullscreen
    ],
    tooltips: {
        controls: false,
        seek: true,
    },
})

$("#make-comment-form").on("submit", function (e) {
    e.preventDefault()

    const form = e.target
    const formData = new FormData(form)

    const comment = formData.get("make-comment")

    let valid = true

    if (!comment) {
        valid = false
        $(form).find(".invalid-message").attr("hidden", false)
    } else {
        $(form).find(".invalid-message").attr("hidden", true)
    }

    if (valid) {
        form.submit()
    } else {
        toastr.error("Không thể bình luận, vui lòng kiểm tra lại bình luận!")
    }
})

function replyComment(e) {
    e.preventDefault()

    const form = e.target
    const formData = new FormData(form)

    const comment = formData.get("make-comment")

    let valid = true

    if (!comment) {
        valid = false
        $(form).find(".invalid-message").attr("hidden", false)
    } else {
        $(form).find(".invalid-message").attr("hidden", true)
    }

    if (valid) {
        form.submit()
    } else {
        toastr.error("Không thể bình luận, vui lòng kiểm tra lại bình luận!")
    }
}

function showReplyCommentForm(target) {
    $(target).closest(".comment").find(".make-comment-form.reply").attr("hidden", false)
}
