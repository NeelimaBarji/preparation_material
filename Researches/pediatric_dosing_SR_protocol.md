# Systematic Review Protocol
## Numeric and Dosing Fidelity in LLM-Generated Pediatric Clinical Documentation and Communication

**Draft protocol v0.1 — August 2026**
**Intended registration:** PROSPERO (with OSF Registries fallback — see §9.1)
**Reporting standard:** PRISMA 2020 (protocol per PRISMA-P)

---

# Part I — The Three Pyramids

## Pyramid 1: Evidence hierarchy — where this review sits, and the honest problem with it

```
                         ▲
                        ╱ ╲
                       ╱SR ╲          ◄── YOUR PAPER
                      ╱ & MA╲              (systematic review; meta-analysis
                     ╱───────╲              unlikely — see §7.2)
                    ╱   RCTs  ╲        ◄── Almost none exist in this space
                   ╱───────────╲
                  ╱   Cohort /  ╲      ◄── A handful (ambient scribe
                 ╱  Quasi-exp't  ╲          before/after studies)
                ╱─────────────────╲
               ╱  Cross-sectional  ╲   ◄── ★ WHERE ~90% OF YOUR
              ╱   benchmark /       ╲       INCLUDED STUDIES WILL LIVE
             ╱    vignette studies   ╲
            ╱─────────────────────────╲
           ╱   Case reports, editorials ╲ ◄── Excluded
          ╱     preprints, commentary    ╲
         ╱───────────────────────────────╲
```

**Read this pyramid as a warning, not a badge.** The classic hierarchy assumes your included studies are clinical trials on human participants. They will not be. The pediatric-dosing LLM literature is almost entirely **cross-sectional in-silico benchmark and vignette studies** — a model is given prompts, outputs are graded against a reference standard, no patient is exposed.

This has three consequences you must design around from day one:

1. **Standard hierarchies rank your entire evidence base near the bottom.** Do not fight this — name it explicitly in your Discussion as a finding about the field's maturity. "The evidence base consists almost entirely of level-4 cross-sectional in-silico evaluations" is a legitimate and quotable conclusion.
2. **Classic risk-of-bias tools don't fit.** RoB 2 assumes randomization; QUADAS-2 assumes a diagnostic index test on patients. You need AI-specific tools (§6).
3. **A meta-analysis is probably not defensible.** Heterogeneity in models, prompts, reference standards, and outcome definitions will be extreme. Plan for structured narrative synthesis, and treat meta-analysis as a contingency only (§7.2).

## Pyramid 2: Your 3-year research program

```
                          ▲
                         ╱ ╲
                        ╱   ╲        YEAR 3 — SYNTHESIS
                       ╱  G  ╲       Harm-weighted evaluation metric
                      ╱───────╲      (re-scores your own Y2 datasets)
                     ╱         ╲
                    ╱  A  │  D  ╲    YEAR 2 — BENCHMARKS
                   ╱ ceil │ real ╲   Primary contributions. Each one
                  ╱  ing  │ izab  ╲  a self-contained artifact + paper.
                 ╱   B stale weight ╲
                ╱───────────────────╲
               ╱                     ╲ YEAR 1 — FOUNDATION
              ╱   SR #1: plain-lang    ╲ Two reviews that define your
             ╱    fidelity (anchor)     ╲ niche, generate your extraction
            ╱   SR #2: THIS PROTOCOL     ╲ taxonomy, and give you a
           ╱     (pediatric dosing)       ╲ citable position before you
          ╱─────────────────────────────────╲ have benchmark results.
```

**Why the reviews go at the base.** They are not throat-clearing. Each one produces two assets you cannot get any other way: (a) a **defensible taxonomy of failure modes**, which becomes the label schema for your benchmarks, and (b) a **documented gap statement** you can cite in every subsequent paper's introduction as evidence of novelty. Doing the benchmarks first means arguing novelty from intuition; doing the reviews first means arguing it from a PRISMA diagram.

**The load-bearing link:** this review's extraction categories (§5.3) should be designed to become the error taxonomy for Idea A and Idea D. Build the extraction form with that second life in mind — it costs nothing now and saves months later.

## Pyramid 3: PRISMA screening funnel — with realistic numbers

```
┌──────────────────────────────────────────────────────────┐
│ IDENTIFICATION                                           │
│ Database searches (7 sources)              n ≈ 1,800–3,500│
│ Other sources (citation chasing, preprints)  n ≈ 40–100  │
└──────────────────────────┬───────────────────────────────┘
                           ▼
┌──────────────────────────────────────────────────────────┐
│ Duplicates removed                         n ≈ 700–1,400 │
│ ───────────────────────────────────────────────────────  │
│ Records screened (title/abstract)          n ≈ 1,100–2,200│
└──────────────────────────┬───────────────────────────────┘
                           ▼
                  Excluded  n ≈ 1,000–2,050
    (adult-only · no numeric/dosing outcome · not LLM ·
     editorial/commentary · no extractable data)
                           ▼
┌──────────────────────────────────────────────────────────┐
│ Full-text assessed for eligibility            n ≈ 90–160 │
└──────────────────────────┬───────────────────────────────┘
                           ▼
                  Excluded  n ≈ 60–130 (with reasons, tabulated)
                           ▼
┌──────────────────────────────────────────────────────────┐
│ ★ STUDIES INCLUDED IN SYNTHESIS            n ≈ 25–45     │
└──────────────────────────────────────────────────────────┘
```

**Be prepared for the low end.** If your final n lands near 15–20, that is not a failed review — but it *does* change the paper. Below roughly 15 studies, reviewers will push you toward reframing as a **scoping review**, which has looser expectations for synthesis and is entirely respectable for an emerging field. Decide your switch threshold *before* you screen (§9.2), and record it in the registered protocol so the decision doesn't look post-hoc.

---

# Part II — The Protocol

## 1. Rationale

Pediatric dosing is arithmetically distinctive: doses are weight-normalized, weight changes rapidly with age, dose ceilings override weight-based calculation above certain thresholds, and neonatal dosing depends on age concepts (gestational, postmenstrual, corrected) with no adult analogue. Tenfold dosing errors are a well-documented source of pediatric harm in conventional practice.

LLMs are being deployed into pediatric documentation and communication workflows — ambient scribes, draft portal replies, generated discharge instructions — where they now generate or transcribe numeric content. Existing evaluations of LLM clinical calculation (MedCalc-Bench and successors) and existing pediatric LLM reviews (JAMA Network Open scoping review 2026; J Pediatr Surg systematic review 2026) do not isolate **numeric and dosing fidelity in the pediatric population** as an outcome class. Evidence on whether tool augmentation resolves the problem is emerging and mixed: LLM agents can invoke calculators successfully, yet a 2026 critique argues existing benchmarks conflate arithmetic execution with problem setup.

No review has synthesized what is known about pediatric numeric fidelity across the documentation and communication pipeline.

## 2. Objectives

**Primary.** To systematically identify, appraise, and synthesize evidence on the accuracy and safety of numeric and dosing content in LLM-generated or LLM-transcribed pediatric clinical documentation and patient/caregiver communication.

**Secondary.**

1. Characterize the distribution of **error types** (arithmetic execution, unit conversion, weight selection, dose-ceiling omission, age-concept confusion, formulation/volume realizability, transcription).
2. Determine whether errors are reported with **direction and magnitude** (overdose vs. underdose; fold-error), rather than as undifferentiated accuracy.
3. Assess whether **tool augmentation / retrieval / verification layers** measurably reduce pediatric numeric error, and which error classes survive them.
4. Map **reference standards** used to adjudicate correctness, and the extent of clinician involvement in grading.
5. Evaluate **reporting completeness** against TRIPOD-LLM and CHART, and identify systemic reporting gaps.

## 3. Eligibility criteria (PICOTS)

| Element | Include | Exclude |
|---|---|---|
| **Population** | Pediatric patients 0–18y, incl. neonates/NICU; or pediatric caregivers; or clinicians documenting pediatric care. Mixed-age studies **only if** pediatric results are separately extractable. | Adult-only; mixed-age with no pediatric stratification; veterinary. |
| **Intervention** | Any LLM / generative AI / transformer-based system generating, summarizing, transcribing, or translating clinical text — incl. ambient scribes, chatbots, note generators. | Classical ML risk-prediction with no text generation; rule-based CDS; non-generative NLP (unless a comparator arm). |
| **Comparator** | Optional. Clinician, reference standard, guideline, calculator, alternative model, or tool-augmented vs. base model. | — |
| **Outcomes** | **≥1 numeric or dosing fidelity outcome**: dose accuracy, calculation error rate, unit-conversion error, numeric transcription fidelity, dose-ceiling adherence, error magnitude/direction, numeric hallucination rate, downstream numeric comprehension by caregivers. | Studies reporting only qualitative satisfaction, time savings, or non-numeric quality with no numeric outcome. |
| **Timing** | 2019 → search date (transformer era; captures pre-ChatGPT BERT-family work). | Pre-2019. |
| **Setting** | Any: inpatient, ED, NICU/PICU, primary care, pharmacy, home/caregiver-facing. In-silico and vignette settings **included**. | — |
| **Study design** | Cross-sectional benchmark/vignette evaluations, diagnostic-accuracy-style studies, cohort, quasi-experimental, RCT, mixed methods with extractable numeric outcomes. | Editorials, commentaries, narrative reviews, conference abstracts without data, case reports. |
| **Language** | English. Non-English recorded and reported as a limitation. | — |
| **Publication type** | Peer-reviewed. **Preprints included in a pre-specified sensitivity analysis only** (see §7.3). | — |

> **Note on preprints.** This field moves faster than journals. Excluding arXiv/medRxiv entirely will make your review stale on arrival; including them uncritically weakens it. The defensible middle: include them, tag them, and run the synthesis with and without.

## 4. Information sources and search strategy

### 4.1 Databases

| # | Source | Rationale |
|---|---|---|
| 1 | MEDLINE/PubMed | Core clinical |
| 2 | Embase | Pharmacology/drug-safety coverage PubMed misses |
| 3 | Scopus **or** Web of Science | Cross-disciplinary + citation chasing |
| 4 | IEEE Xplore | Engineering venues |
| 5 | ACM Digital Library | CS/HCI venues |
| 6 | CINAHL | Nursing — **important**, much pediatric dose-administration literature lives here |
| 7 | arXiv + medRxiv | Preprints (sensitivity arm) |

Supplement with: backward/forward citation chasing on all included studies, and hand-searching JAMIA, npj Digital Medicine, JMIR, Journal of Biomedical Informatics, Pediatrics, and Pediatric Research.

### 4.2 Draft PubMed strategy

Your Boolean string is a solid starting scaffold, but as written it will **miss the dosing literature entirely** — nothing in it requires a numeric or dosing outcome, so you would retrieve thousands of ambient-scribe-satisfaction papers and few dosing papers. Add a mandatory third concept block:

```
(   "large language model*"[tiab] OR LLM*[tiab] OR "generative AI"[tiab]
 OR GenAI[tiab] OR "ambient AI"[tiab] OR GPT*[tiab] OR ChatGPT[tiab]
 OR "transformer model*"[tiab] OR "foundation model*"[tiab]
 OR "artificial intelligence"[MeSH] OR "Natural Language Processing"[MeSH] )
AND
(   pediatric*[tiab] OR paediatric*[tiab] OR child*[tiab] OR infant*[tiab]
 OR neonat*[tiab] OR newborn*[tiab] OR toddler*[tiab] OR adolescen*[tiab]
 OR NICU[tiab] OR PICU[tiab] OR "Pediatrics"[MeSH] OR "Child"[MeSH]
 OR "Infant"[MeSH] OR "Infant, Newborn"[MeSH] OR "Adolescent"[MeSH] )
AND
(   dos*[tiab] OR "drug dosage calculation*"[tiab] OR mg/kg[tiab]
 OR "weight-based"[tiab] OR "medication error*"[tiab] OR prescrib*[tiab]
 OR "unit conversion*"[tiab] OR calculat*[tiab] OR numeric*[tiab]
 OR arithmetic[tiab] OR "Drug Dosage Calculations"[MeSH]
 OR "Medication Errors"[MeSH] OR "Drug Prescriptions"[MeSH] )
AND
(   "clinical documentation"[tiab] OR "electronic health record*"[tiab]
 OR EHR[tiab] OR EMR[tiab] OR "discharge summar*"[tiab]
 OR "discharge instruction*"[tiab] OR "progress note*"[tiab]
 OR "patient communication"[tiab] OR "clinical communication"[tiab]
 OR "after-visit summar*"[tiab] OR scribe*[tiab] OR chatbot*[tiab]
 OR "Electronic Health Records"[MeSH] OR "Documentation"[MeSH] )
```

**Run the fourth block as optional in a sensitivity search.** Blocks 1+2+3 alone is your primary search; adding block 4 narrows to the documentation/communication context. Report both yields — if block 4 cuts your n below viability, drop it and widen the review's framing to pediatric dosing fidelity generally.

**Validate before you commit.** Assemble a seed set of 8–10 papers you already know should be captured (the Pediatric Research dosing paper, the JCM cross-language medication-error paper, MedCalc-Bench, the npj tool-use paper). If your string doesn't retrieve all of them, it is not ready. Document this validation step — reviewers value it and almost nobody reports it.

### 4.3 Translation and management

Translate to each database's syntax (Emtree for Embase, CINAHL headings). Record every string verbatim with date run and yield — this becomes a required appendix. Deduplicate in Zotero or Rayyan; do screening in **Rayyan** (free, built for this, supports blinded dual screening).

## 5. Study selection and data collection

### 5.1 Screening

Two-stage: title/abstract → full text. PRISMA 2020 flow diagram with exclusion reasons tabulated at full-text stage.

**The solo-reviewer problem.** PRISMA expects dual independent screening. You are working solo and after-hours. Options, in descending order of credibility:

1. **Recruit one collaborator** for independent screening — ideally a pediatric clinician or pharmacist. This also solves your clinical-adjudication gap in §5.4 and strengthens the paper substantially. Strongly recommended.
2. **Partial dual screening:** a second reviewer independently screens a random 20% sample; report Cohen's κ. Acceptable at most target venues if disclosed.
3. **Solo with a documented consistency check:** re-screen a 10% random sample yourself ≥2 weeks later, report intra-rater agreement. Weakest option; disclose as a limitation.

Do **not** use an LLM as your second screener and present it as independent dual screening. LLM-assisted screening is an active research area and can be defensible as a *pre-filter with human confirmation of all exclusions*, but presenting it as a second human reviewer will not survive peer review — and given your review is *about* LLM reliability, the irony will be noticed.

### 5.2 Piloting

Pilot the extraction form on 5 studies, revise, then extract. Report that you piloted — most reviews skip this and it's a cheap credibility win.

### 5.3 Data extraction fields

Design these to double as your future benchmark's label schema.

**Study metadata:** citation, year, country, venue type, funding, COI, peer-reviewed vs. preprint.

**Population:** age range, subgroup (neonate/infant/child/adolescent), setting, whether real patient data or synthetic/vignette, sample size (n prompts / n cases / n patients).

**Model & configuration:** model name and version, **version date** (critical — "GPT-4" in 2023 ≠ 2024), open vs. closed weights, temperature and decoding parameters, prompting strategy (zero-shot / few-shot / CoT), tool augmentation (calculator / retrieval / none), agentic vs. single-call, system prompt reported (Y/N).

**Task:** generation vs. verification vs. transcription vs. translation; output artifact (note, discharge instruction, portal reply, dose recommendation).

**Reference standard:** source (guideline / label / pharmacist / clinician consensus / calculator), number and qualifications of graders, blinding, inter-rater reliability reported (Y/N + statistic).

**Numeric outcomes — the core table:**

- overall dose/numeric accuracy (%)
- error rate by type: arithmetic execution · unit conversion · weight selection · stale weight · dose-ceiling omission · age-concept (GA/PMA/corrected) confusion · formulation/volume realizability · numeric transcription
- **error direction** (over vs. under) — extract explicitly; expect most studies not to report it, which is itself a headline finding
- **error magnitude** (fold-error, % deviation, or category)
- **clinically significant error rate**, and the study's definition of "clinically significant"
- harm framework applied (e.g., NCC MERP index) — Y/N
- tool-augmented vs. base comparison, if present

**Reporting quality:** TRIPOD-LLM adherence (item-level), CHART adherence for chatbot studies.

**Free-text:** author-reported limitations; any pediatric-specific failure mode described narratively but not quantified. *Harvest these aggressively — they are where your next paper's hypotheses come from.*

### 5.4 Clinical adjudication

You will encounter judgement calls about whether a reported error is clinically meaningful. Pre-specify that ambiguous cases are adjudicated by a pediatric pharmacist or clinician collaborator. If you cannot secure one, pre-specify that you extract only author-reported significance classifications and do not impose your own — and state this as a limitation. Do not quietly make these calls yourself as a non-clinician; it is the single most likely thing to be challenged in review.

## 6. Risk of bias assessment

No single tool fits. Use a **tiered approach** and justify it explicitly — this is a genuine methodological contribution in itself.

| Study type | Primary tool |
|---|---|
| LLM Q&A / benchmark / vignette evaluations (the bulk) | **AQAT:RoB** — Alberta Quality Assessment Tool: Risk of Bias, purpose-built for medical LLM question-answer studies (JMIR, 2026) |
| Diagnostic-accuracy-framed studies | **QUADAS-2**, with **QUADAS-AI** modifications |
| Prediction-model studies | **PROBAST+AI** |
| RCTs (if any) | **RoB 2** |
| Non-randomized interventional (e.g., scribe before/after) | **ROBINS-I** |
| All studies (reporting completeness, not RoB) | **TRIPOD-LLM**; **CHART** for chatbot studies |

**Add a pediatric-specific bias domain of your own.** No existing tool asks whether a study verified that weights were age-appropriate, whether dose ceilings were represented in the test set, or whether neonatal age concepts were disambiguated. Propose a short domain set covering: pediatric case-mix representativeness (does the test set span the weight range where ceilings bind?), reference-standard pediatric validity, and age-stratified reporting. Defining this is a small original methodological contribution and gives the paper more to say than "quality was mixed."

## 7. Synthesis

### 7.1 Primary approach — structured narrative synthesis

Group by: (a) error type, (b) task type (generation / verification / transcription / translation), (c) augmentation (base vs. tool-augmented), (d) age stratum. Present harvest plots or a bubble matrix of error type × age stratum × study count to expose where evidence is dense and where it is absent. **The empty cells are your gap statement** — and in a field this young, the map of absences is likely to be your most-cited figure.

### 7.2 Meta-analysis — contingency only

Pool only if ≥3 studies share model family, task, outcome definition, and reference standard. Honestly: this is unlikely. If pooling is attempted, use random-effects, report I², and pre-specify subgroup analyses (age stratum; tool-augmented vs. not; open vs. closed weights). **Do not pool heterogeneous "accuracy" percentages across different tasks and reference standards** — it produces a meaningless number and is the most common fatal flaw in AI systematic reviews right now. State in the protocol that you will not.

### 7.3 Pre-specified sensitivity and subgroup analyses

- Peer-reviewed only vs. including preprints
- Real patient data vs. synthetic/vignette
- Open-weight vs. closed-weight models
- Neonatal/NICU vs. older pediatric
- Tool-augmented vs. base model
- Studies with vs. without clinician graders

### 7.4 Certainty of evidence

Apply **GRADE** where meaningful, with an explicit caveat: GRADE is built for clinical outcomes in human participants and maps awkwardly onto in-silico benchmarks. State that you apply it descriptively to outcome domains rather than as a formal pooled-estimate rating, and explain why. Reviewers respect a defended deviation far more than a silent one.

## 8. Reporting

PRISMA 2020 (with PRISMA-S for the search) as the backbone. Appendices: full search strings per database with dates and yields; excluded-at-full-text table with reasons; extraction form; RoB assessments per study; seed-set validation results.

## 9. Practical notes

### 9.1 Registration — a real obstacle to plan for

PROSPERO requires reviews to have a **health-related outcome**. A review whose included studies are entirely in-silico LLM benchmarks with no human participants sits in a grey zone and **may be rejected as out of scope**.

Mitigate by:

- Framing the outcome as **patient safety / medication error**, which is health-related, rather than as "model accuracy," which is not. This framing is honest — dosing fidelity *is* a safety outcome — and materially improves acceptance odds.
- Having a fallback ready: **OSF Registries** (free, accepts any design, timestamped) or **INPLASY**. A registered OSF protocol satisfies most journals' prospective-registration requirements.
- **Register before screening begins.** Retrospective registration is detectable and damages credibility.

### 9.2 Pre-specify your scoping-review switch

Write into the protocol: *"If fewer than 15 studies meet inclusion criteria, the review will be reported as a systematic scoping review following PRISMA-ScR, with the same search and extraction methods."* Deciding this in advance converts a potential weakness into a pre-registered contingency.

### 9.3 Realistic timeline — solo, after-hours

Roughly 6–9 months at 6–10 hours/week. Anyone promising 3 months is assuming a team.

| Phase | Weeks | Notes |
|---|---|---|
| Scoping searches, seed-set validation, refine question | 1–3 | Confirm the review is viable *before* investing further |
| Draft + register protocol | 4–6 | PROSPERO/OSF; recruit collaborator here if possible |
| Run searches, deduplicate | 7–8 | Single concentrated push |
| Title/abstract screening | 9–16 | The grind. ~2,000 records ≈ 25–35 hours |
| Full-text screening | 17–20 | |
| Pilot + full extraction | 21–30 | Heaviest cognitive load; don't compress |
| Risk of bias | 31–34 | Can overlap extraction |
| Synthesis, figures, harvest plots | 35–38 | |
| Drafting | 39–44 | |
| Internal revision, PRISMA checklist, appendices | 45–48 | |

**Two things that most often derail solo reviews:** underestimating title/abstract screening (budget 45–60 seconds per record and do it in short sessions — accuracy collapses after ~45 minutes), and letting the search go stale. **Re-run your search before submission** and report the update date; an 8-month-old search in a field moving this fast will be flagged.

### 9.4 Venue

Given the methodological framing (a novel tiered RoB approach plus a pediatric bias domain), **JAMIA** or **Journal of Biomedical Informatics** fit better than a pure clinical journal. **npj Digital Medicine** if the synthesis yields a strong, quotable safety signal. **JMIR** is a reasonable and faster-turnaround third option, and it has published the AQAT:RoB tool you'd be using — a small alignment advantage.

---

## Sources

- [The TRIPOD-LLM reporting guideline for studies using large language models — Nature Medicine](https://www.nature.com/articles/s41591-024-03425-5)
- [TRIPOD-LLM — PMC full text](https://pmc.ncbi.nlm.nih.gov/articles/PMC12104976/)
- [The Alberta Quality Assessment Tool: Risk of Bias (AQAT:RoB) for Medical Large Language Model Question-Answer Studies — JMIR 2026](https://www.jmir.org/2026/1/e87057)
- [Critical Appraisal Tools for Evaluating Artificial Intelligence in Clinical Studies: Scoping Review — JMIR 2025](https://www.jmir.org/2025/1/e77110)
- [Revised Tool for Quality Assessment of Diagnostic Accuracy Studies Using AI (QUADAS-AI): Protocol — PMC](https://pmc.ncbi.nlm.nih.gov/articles/PMC11447435/)
- [PROBAST+AI — Cochrane Methods spotlight (PDF)](https://www.cochrane.org/sites/default/files/uploads/PDFs/Methods%20reports/2025/Spotlight%20On_Prognosis.pdf)
- [Reporting guideline for chatbot health advice studies: the CHART statement — PMC](https://pmc.ncbi.nlm.nih.gov/articles/PMC12320030/)
- [CHART statement — BJS](https://academic.oup.com/bjs/article/112/8/znaf142/8220472)
- [How to register a systematic review in PROSPERO — CRD York](https://www.crd.york.ac.uk/PROSPERO/help/register)
- [Can large language models assist with pediatric dosing accuracy? — Pediatric Research](https://www.nature.com/articles/s41390-025-03980-8)
- [Large language models and clinical calculations: to err is human and machines are not exempt — Pediatric Research](https://www.nature.com/articles/s41390-025-04166-y)
- [Large language model agents can use tools to perform clinical calculations — npj Digital Medicine](https://www.nature.com/articles/s41746-025-01475-8)
- [MedCalc-Bench: Evaluating Large Language Models for Medical Calculations — NeurIPS 2024](https://proceedings.neurips.cc/paper_files/paper/2024/hash/99e81750f3fdfcaf9613db2dbf4bd623-Abstract-Datasets_and_Benchmarks_Track.html)
- [MedCalc-Bench Doesn't Measure What You Think (preprint)](https://arxiv.org/html/2603.02222v1)
- [Evaluating the Performance of AI Large Language Models in Detecting Pediatric Medication Errors Across Languages — JCM](https://doi.org/10.3390/jcm15010162)
- [Tenfold medication errors: 5 years' experience at a university-affiliated pediatric hospital — PubMed](https://pubmed.ncbi.nlm.nih.gov/22473367/)
- [Large Language Models Using Clinical Text in Pediatrics: A Scoping Review — PMC](https://pmc.ncbi.nlm.nih.gov/articles/PMC13019234/)
- [Large language models for electronic health records in pediatric and surgical care: A systematic review — J Pediatr Surg](https://www.jpedsurg.org/article/S0022-3468(26)00039-4/fulltext)
