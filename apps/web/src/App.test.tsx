import { cleanup, render, screen } from '@testing-library/react'
import { afterEach, describe, expect, it, vi } from 'vitest'
import App from './App'

afterEach(() => {
  cleanup()
  vi.unstubAllGlobals()
})

describe('App API health', () => {
  it('displays the loading state while waiting for the API', () => {
    vi.stubGlobal(
      'fetch',
      vi.fn(() => new Promise<Response>(() => {})),
    )

    render(<App />)

    expect(screen.getByRole('status')).toHaveTextContent('Checking API health')
  })

  it('displays the API status after a successful response', async () => {
    vi.stubGlobal(
      'fetch',
      vi.fn().mockResolvedValue(
        new Response(JSON.stringify({ status: 'UP' }), {
          status: 200,
          headers: {
            'Content-Type': 'application/json',
          },
        }),
      ),
    )

    render(<App />)

    expect(await screen.findByText('API status: UP')).toBeInTheDocument()
  })

  it('displays an error when the API request fails', async () => {
    vi.stubGlobal(
      'fetch',
      vi.fn().mockRejectedValue(new Error('Network unavailable')),
    )

    render(<App />)

    expect(await screen.findByRole('alert')).toHaveTextContent(
      'Unable to reach the API: Network unavailable',
    )
  })
})
