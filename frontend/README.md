# Blog Frontend

This is a lightweight React-based interface for the Blog Application backend. It uses CDN-hosted React so no build tools are required. The page lists posts, lets you filter by tag, search by keyword, like posts, bookmark favorites, and view or add comments directly from the browser.
You can also show the most liked posts with the **Top Posts** button.
Authors can create new posts using a built-in Markdown editor with live preview.

## Running

Serve the `frontend` directory using any static file server. For example:

```sh
python -m http.server 3000
```

Then open [http://localhost:3000/index.html](http://localhost:3000/index.html) in your browser. The page fetches posts from `http://localhost:8080` by default.
