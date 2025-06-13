# Blog Frontend

This is a simple React-based interface for the Blog Application backend. It uses CDN-hosted React so no build tools are required.

## Running

Serve the `frontend` directory using any static file server. For example:

```sh
python -m http.server 3000
```

Then open [http://localhost:3000/index.html](http://localhost:3000/index.html) in your browser. The page fetches posts from `http://localhost:8080` by default.
