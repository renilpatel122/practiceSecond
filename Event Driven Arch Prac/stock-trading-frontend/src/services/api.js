import axios from 'axios';

export default axios.create({
  baseURL: 'http://localhost:8080', // Replace with Order Service base URL
});
