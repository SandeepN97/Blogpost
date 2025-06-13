const { useState, useEffect } = React;

function App() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/post/getAllPost')
      .then(res => res.json())
      .then(setPosts)
      .catch(err => console.error('Failed to load posts', err));
  }, []);

  return (
    <div>
      <h1>Blog Posts</h1>
      <ul>
        {posts.map(post => (
          <li key={post.id}>{post.title}</li>
        ))}
      </ul>
    </div>
  );
}

ReactDOM.createRoot(document.getElementById('root')).render(<App />);
