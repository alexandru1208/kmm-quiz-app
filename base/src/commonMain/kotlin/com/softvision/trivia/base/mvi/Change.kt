package com.softvision.trivia.base.mvi

/**
 * Generic Change that will alter the UI.
 * Every intent should result in one ore more changes
 */
interface Change

/**
 * A Change that is directly passed to UI to be handled only once
 * This should be used for navigation or UI changes that are temporary
 */
interface Event : Change

/**
 * A Change that used to mutate the current state of the UI
 */
interface Mutation : Change