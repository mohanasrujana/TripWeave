import {
  cleanup,
  fireEvent,
  render,
  screen,
  waitFor,
} from '@testing-library/react'
import { afterEach, describe, expect, it, vi } from 'vitest'
import TripForm from './TripForm'

const savedTrip = {
  id: '20000000-0000-0000-0000-000000000001',
  name: 'Boston weekend',
  startDate: '2026-10-10',
  endDate: '2026-10-12',
  timeZone: 'America/New_York',
  creatorId: '10000000-0000-0000-0000-000000000001',
  createdAt: '2026-09-11T12:00:00Z',
}

afterEach(() => {
  cleanup()
  vi.unstubAllGlobals()
})

describe('TripForm', () => {
  it('creates and renders a saved trip', async () => {
    const fetchMock = vi.fn().mockResolvedValue(
      new Response(JSON.stringify(savedTrip), {
        status: 201,
        headers: { 'Content-Type': 'application/json' },
      }),
    )
    vi.stubGlobal('fetch', fetchMock)

    render(<TripForm />)

    fireEvent.change(screen.getByLabelText('Trip name'), {
      target: { value: savedTrip.name },
    })
    fireEvent.change(screen.getByLabelText('Start date'), {
      target: { value: savedTrip.startDate },
    })
    fireEvent.change(screen.getByLabelText('End date'), {
      target: { value: savedTrip.endDate },
    })
    fireEvent.change(screen.getByLabelText('Time zone'), {
      target: { value: savedTrip.timeZone },
    })
    fireEvent.change(screen.getByLabelText('Creator ID'), {
      target: { value: savedTrip.creatorId },
    })

    const submitButton = screen.getByRole('button', { name: 'Create trip' })
    fireEvent.submit(submitButton.closest('form')!)

    expect(screen.getByRole('button', { name: 'Creating…' })).toBeDisabled()
    expect(
      await screen.findByRole('article', { name: 'Saved trip' }),
    ).toHaveTextContent('Boston weekend')
    expect(screen.getByText(`Trip ID: ${savedTrip.id}`)).toBeInTheDocument()

    expect(fetchMock).toHaveBeenCalledWith('/api/trips', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        name: savedTrip.name,
        startDate: savedTrip.startDate,
        endDate: savedTrip.endDate,
        timeZone: savedTrip.timeZone,
        creatorId: savedTrip.creatorId,
      }),
    })
  })

  it('renders the API error and restores the submit button', async () => {
    vi.stubGlobal(
      'fetch',
      vi.fn().mockResolvedValue(
        new Response(JSON.stringify({ message: 'Unknown time zone' }), {
          status: 400,
          headers: { 'Content-Type': 'application/json' },
        }),
      ),
    )

    render(<TripForm />)

    fireEvent.submit(
      screen.getByRole('button', { name: 'Create trip' }).closest('form')!,
    )

    expect(await screen.findByRole('alert')).toHaveTextContent(
      'Unable to create trip: Unknown time zone',
    )
    await waitFor(() => {
      expect(screen.getByRole('button', { name: 'Create trip' })).toBeEnabled()
    })
  })
})
