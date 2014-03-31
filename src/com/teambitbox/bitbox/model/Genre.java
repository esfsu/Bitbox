package com.teambitbox.bitbox.model;

public enum Genre {
  BLUES("Blue", 0),
  CLASSIC_ROCK("Classic Rock", 1),
  COUNTRY("Country", 2),
  DANCE("Dance", 3),
  DISCO("Disco", 4),
  FUNK("Funk", 5),
  GRUNGE("Grunge", 6),
  HIP_HOP("Hip Hop", 7),
  JAZZ("Jazz", 8),
  METAL("Metal", 9),
  NEW_AGE("New Age", 10),
  OLDIES("Oldies", 11),
  OTHER("Other", 12),
  POP("Pop", 13),
  R_B("R&B", 14),
  RAP("Rap", 15),
  REGGAE("Reggae", 16),
  ROCK("Rock", 17),
  TECHNO("Techno", 18),
  INDUSTRIAL("Industrial", 19),
  ALTERNATIVE("Alternative", 20),
  SKA("Ska", 21),
  DEATH_METAL("Death Metal", 22),
  PRANKS("Pranks", 23),
  SOUNDTRACK("Soundtrack", 24),
  EURO_TECHNO("Euro Techno", 25),
  AMBIENT("Ambient", 26),
  TRIP_HOP("Trip Hop", 27),
  VOCAL("Vocal", 28),
  JAZZ_FUNK("Jazz Funk", 29),
  FUSION("Fusion", 30),
  TRANCE("Trance", 31),
  CLASSICAL("Classical", 32),
  INSTRUMENTAL("Instrumental", 33),
  ACID("Acid", 34),
  HOUSE("House", 35),
  GAME("Game", 36),
  SOUND_CLIP("Sound Clip", 37),
  GOSPEL("Gospel", 38),
  NOISE("Noise", 39),
  ALTERNROCK("Alternrock", 40),
  BASS("Bass", 41),
  SOUL("Soul", 42),
  PUNK("Punk", 43),
  SPACE("Space", 44),
  MEDITATIVE("Meditative", 45),
  INSTRUMENTAL_POP("Instrumental Pop", 46),
  INSTRUMENTAL_ROCK("Instrumental Rock", 47),
  ETHNIC("Ethnic", 48),
  GOTHIC("Gothic", 49),
  DARKWAVE("Darkwave", 50),
  TECHNO_INDUSTRIAL("Techno Industrial", 51),
  ELECTRONIC("Electronic", 52),
  POP_FOLK("Pop Folk", 53),
  EURODANCE("Eurodance", 54),
  DREAM("Dream", 55),
  SOUTHERN_ROCK("Southern Rock", 56),
  COMEDY("Comedy", 57),
  CULT("Cult", 58),
  GANGSTA("Gangsta", 59),
  TOP_40("Top 40", 60),
  CHRISTIAN_RAP("Chrisitian Rap", 61),
  POP_FUNK("Pop Funk", 62),
  JUNGLE("Jungle", 63),
  NATIVE_AMERICAN("Native American", 64),
  CABARET("Cabaret", 65),
  NEW_WAVE("New Wave", 66),
  PSYCHADELIC("Psychadelic", 67),
  RAVE("Rave", 68),
  SHOWTUNES("Showtunes", 69),
  TRAILER("Trailer", 70),
  LO_FI("Lo-Fi", 71),
  TRIBAL("Tribal", 72),
  ACID_PUNK("Acid Punk", 73),
  ACID_JAZZ("Acid Jazz", 74),
  POLKA("Polka", 75),
  RETRO("Retro", 76),
  MUSICAL("Musical", 77),
  ROCK_ROLL("Rock & Roll", 78),
  HARD_ROCK("Hard Rock", 79),
  GENRE_INVALID("", 999);
    
  private Genre(final String name, final int id3) {
    mName = name;
    mId3 = id3;
  }
  
  private final String mName;
  private final int mId3;

  @Override
  public String toString() {
      return mName;
  }
  
  public int toInt(int value)
  {
    return mId3;
  }
}
