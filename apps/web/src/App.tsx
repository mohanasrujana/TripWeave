import { useEffect, useState } from 'react'
import './App.css'

type HealthState =
  | { kind: 'loading' }
  | { kind: 'success'; apiStatus: string }
  | { kind: 'error'; message: string }

function App() {
  const [health, setHealth] = useState<HealthState>({ kind: 'loading' })

  useEffect(() => {
    const controller = new AbortController()

    async function loadHealth() {
      try {
        const response = await fetch('/api/health', {
          signal: controller.signal,
        })

        if (!response.ok) {
          throw new Error(`API returned status ${response.status}`)
        }

        const data: unknown = await response.json()

        if (
          typeof data !== 'object' ||
          data === null ||
          !('status' in data) ||
          typeof data.status !== 'string'
        ) {
          throw new Error('API returned an invalid health response')
        }

        setHealth({
          kind: 'success',
          apiStatus: data.status,
        })
      } catch (error) {
        if (error instanceof DOMException && error.name === 'AbortError') {
          return
        }

        setHealth({
          kind: 'error',
          message: error instanceof Error ? error.message : 'Unknown error',
        })
      }
    }

    void loadHealth()

    return () => controller.abort()
  }, [])

  return (
    <main>
      <h1>TripWeave</h1>
      <p>Collaborative group-trip planning</p>

      <section aria-labelledby="api-health-heading">
        <h2 id="api-health-heading">API health</h2>

        {health.kind === 'loading' && <p role="status">Checking API health…</p>}

        {health.kind === 'success' && (
          <p role="status">API status: {health.apiStatus}</p>
        )}

        {health.kind === 'error' && (
          <p role="alert">Unable to reach the API: {health.message}</p>
        )}
      </section>
    </main>
  )
}

export default App
