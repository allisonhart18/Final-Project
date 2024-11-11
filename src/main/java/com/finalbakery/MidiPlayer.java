package com.finalbakery;

import javax.sound.midi.*;
import java.io.File;
import java.util.LinkedList;

public class MidiPlayer {
    private LinkedList<Sequence> midiFiles;
    private Sequencer sequencer;

    public MidiPlayer() {
        midiFiles = new LinkedList<>();
        initializeSequencer();
    }

    private void initializeSequencer() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void loadMidiFiles(String... filePaths) {
        try {
            for (String filePath : filePaths) {
                Sequence sequence = MidiSystem.getSequence(new File(filePath));
                midiFiles.add(sequence);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playAll() {
        if (sequencer == null || midiFiles.isEmpty()) return;

        try {
            for (Sequence sequence : midiFiles) {
                sequencer.setSequence(sequence);
                sequencer.start();
                while (sequencer.isRunning()) {
                    Thread.sleep(100);  // Allows playback to complete
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (sequencer != null && sequencer.isOpen()) {
            sequencer.close();
        }
    }
}
