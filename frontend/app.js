const { useState, useEffect, useCallback } = React;

function App() {
  const [posts, setPosts] = useState([]);
  const [tag, setTag] = useState('');
  const [query, setQuery] = useState('');

  const fetchPosts = useCallback((tagName = '', search = '') => {
    let url = 'http://localhost:8080/post/getAllPost';
    if (search) {
      url = `http://localhost:8080/post/search?q=${encodeURIComponent(search)}`;
    } else if (tagName) {
      url = `http://localhost:8080/post/tag/${encodeURIComponent(tagName)}`;
    }
    fetch(url)
      .then(res => res.json())
      .then(setPosts)
      .catch(err => console.error('Failed to load posts', err));
  }, []);

  useEffect(() => {
    fetchPosts();
  }, [fetchPosts]);

  const handleLike = (id) => {
    fetch(`http://localhost:8080/post/${id}/like`, { method: 'POST' })
      .then(() => fetchPosts(tag, query))
      .catch(err => console.error('Failed to like post', err));
  };

  const handleFilter = (e) => {
    e.preventDefault();
    fetchPosts(tag, query);
  };

  const clearFilter = () => {
    setTag('');
    fetchPosts('', query);
  };

  const handleSearch = (e) => {
    e.preventDefault();
    fetchPosts(tag, query);
  };

  const clearSearch = () => {
    setQuery('');
    fetchPosts(tag, '');
  };

  return (
    <div>
      <h1>Blog Posts</h1>
      <form onSubmit={handleFilter} className="filter-form">
        <input
          value={tag}
          onChange={(e) => setTag(e.target.value)}
          placeholder="Filter by tag..."
        />
        <button type="submit">Apply</button>
        <button type="button" onClick={clearFilter}>Clear</button>
      </form>
      <form onSubmit={handleSearch} className="filter-form">
        <input
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          placeholder="Search posts..."
        />
        <button type="submit">Search</button>
        <button type="button" onClick={clearSearch}>Clear</button>
      </form>
      <ul className="post-list">
        {posts.map(post => (
          <li key={post.id} className="post">
            <h2>{post.title}</h2>
            <p>{post.content}</p>
            <div className="likes">
              <span>{post.likes} likes</span>
              <button onClick={() => handleLike(post.id)}>Like</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

ReactDOM.createRoot(document.getElementById('root')).render(<App />);
