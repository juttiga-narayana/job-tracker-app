/**
 * main.jsx
 * ------------------------
 * Entry point of the React application.
 *
 * Purpose:
 *  - Bootstraps the React app and attaches it to the DOM.
 *  - Wraps the application with BrowserRouter for client-side routing.
 *  - Uses StrictMode to highlight potential issues in development.
 *
 * Imported Dependencies:
 *  - StrictMode (React): Helps identify potential problems in the application.
 *  - createRoot (react-dom/client): Renders the root React component into the DOM.
 *  - App: The root component that defines the overall app structure and routes.
 *  - BrowserRouter (react-router-dom): Enables navigation and routing in the app.
 *
 * Render Flow:
 *  - `createRoot` attaches the React app to the <div id="root"> element in index.html.
 *  - `BrowserRouter` wraps <App /> to provide routing context across the app.
 *  - `StrictMode` wraps <App /> to enforce additional checks during development.
 *
 * Notes:
 *  - This file should remain minimal since its main responsibility is app initialization.
 */

import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import { BrowserRouter } from 'react-router-dom'

createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <StrictMode>
      <App />
    </StrictMode>
    ,
  </BrowserRouter>
)
